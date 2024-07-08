package com.c3dev.bagueton.ui.ui.screens.tools

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.screens.recipes.BaguetonViewModel
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolScreenCalc(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel,
    toolsViewModel: ToolsViewModel
) {
    LaunchedEffect(key1 = true) {
        baguetonViewModel.loadRecipes()
    }

    Scaffold(
        bottomBar = { MyBottomAppBar(navHostController = navHostController) },
        topBar = { SearchBar(baguetonViewModel = baguetonViewModel, navHostController = navHostController) },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            // Menu déroulant pour choisir la recette
            ExposedDropdownMenuBox(
                expanded = toolsViewModel.expanded.value,
                onExpandedChange = { toolsViewModel.expanded.value = !toolsViewModel.expanded.value },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = toolsViewModel.selectedOptionText.value,
                    onValueChange = { toolsViewModel.selectedOptionText.value = it },
                    label = { Text("Recettes") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = toolsViewModel.expanded.value) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                )
                // Filtrer les options basées sur la valeur du TextField
                val filteringOptions = baguetonViewModel.recipeList.filter { it.title.contains(toolsViewModel.selectedOptionText.value, ignoreCase = true) }
                if (filteringOptions.isNotEmpty()) {
                    DropdownMenu(
                        modifier = Modifier
                            .background(Color.White)
                            .exposedDropdownSize(true),
                        properties = PopupProperties(focusable = false),
                        expanded = toolsViewModel.expanded.value,
                        onDismissRequest = { toolsViewModel.expanded.value = false },
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption.title) },
                                onClick = {
                                    toolsViewModel.selectedOptionText.value = selectionOption.title
                                    toolsViewModel.selectedRecipe.value = selectionOption
                                    toolsViewModel.expanded.value = false
                                    toolsViewModel.updateNewQuantity()
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TextField pour entrer un nombre
            TwoTextFields(toolsViewModel)
            Spacer(modifier = Modifier.height(16.dp))

            val totalWeight = toolsViewModel.totalWeight
            val finalWeight = if (toolsViewModel.totalWeightRequest.value.isNotEmpty()) {
                toolsViewModel.totalWeightRequest.value.toInt()
            } else {
                totalWeight * toolsViewModel.multiplier
            }
            Text("Poids total : $finalWeight grammes")

            Spacer(modifier = Modifier.height(16.dp))

            // Affichage des ingrédients de la recette sélectionnée
            toolsViewModel.selectedRecipe.value?.let { recipe ->
                Spacer(modifier = Modifier.height(16.dp))
                recipe.ingredients?.forEach { ingredient ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .border(1.dp, MaterialTheme.colorScheme.onBackground)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        ingredient.ingredient?.let { it1 ->
                            Text(
                                text = it1, modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                        }
                        ingredient.quantity?.let { quantity ->
                            val updatedQuantity = toolsViewModel.newQuantity(quantity)
                            Text(
                                text = "$updatedQuantity g",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f, true))
        }
    }
}

@Composable
fun TwoTextFields(toolsViewModel: ToolsViewModel) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = toolsViewModel.multiplication.value,
                onValueChange = { newText ->
                    if (newText.all { it.isDigit() }) {
                        toolsViewModel.multiplication.value = newText
                        toolsViewModel.updateNewQuantity()
                    }
                },
                label = { Text("Multiplicateur") },
                placeholder = { Text("Multiplicateur") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                enabled = toolsViewModel.totalWeightRequest.value.isEmpty(),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            TextField(
                value = toolsViewModel.totalWeightRequest.value,
                onValueChange = { newText ->
                    if (newText.all { it.isDigit() }) {
                        toolsViewModel.totalWeightRequest.value = newText
                        toolsViewModel.updateNewQuantity()
                    }
                },
                label = { Text("Poids total voulu") },
                placeholder = { Text("Poids") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                enabled = toolsViewModel.multiplication.value.isEmpty(),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ToolScreenCalcPreview() {
    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ToolScreenCalc(baguetonViewModel = BaguetonViewModel(), toolsViewModel = ToolsViewModel())
        }
    }
}
