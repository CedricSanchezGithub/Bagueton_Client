package com.c3dev.bagueton.ui.ui.screens

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.c3dev.bagueton.ui.AccountViewModel
import com.c3dev.bagueton.ui.BaguetonViewModel
import com.c3dev.bagueton.ui.model.RecipeBean
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolScreenCalc(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel,
    accountViewModel: AccountViewModel? = null
) {
    LaunchedEffect(key1 = true) {
        baguetonViewModel.loadRecipes()
    }

    val multiplication by remember { mutableStateOf(baguetonViewModel.multiplication) }
    val recipeList by remember { mutableStateOf(baguetonViewModel.recipeList) }
    var selectedRecipe by remember { mutableStateOf<RecipeBean?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            baguetonViewModel = baguetonViewModel,
            navHostController = navHostController
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menu déroulant pour choisir la recette
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                // Le `menuAnchor` modifier doit être passé au TextField pour corriger.
                modifier = Modifier.menuAnchor(),
                value = selectedOptionText,
                onValueChange = { selectedOptionText = it },
                label = { Text("Recettes") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
            )
            // Filtrer les options basées sur la valeur du TextField
            val filteringOptions = recipeList.filter { it.title.contains(selectedOptionText, ignoreCase = true) }
            if (filteringOptions.isNotEmpty()) {
                DropdownMenu(
                    modifier = Modifier
                        .background(Color.White)
                        .exposedDropdownSize(true),
                    properties = PopupProperties(focusable = false),
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    filteringOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption.title) },
                            onClick = {
                                selectedOptionText = selectionOption.title
                                selectedRecipe = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TextField pour entrer un nombre
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text("Enter your text") },
            placeholder = { Text("Type here...") },
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        // Affichage des ingrédients de la recette sélectionnée
        selectedRecipe?.let {
            Text(text = "Ingrédients pour ${it.title}:")
            Spacer(modifier = Modifier.height(16.dp))
            it.ingredients?.forEach { ingredient ->
                Row(modifier = Modifier
                    .padding(vertical = 8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.onBackground)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ingredient.ingredient?.let { it1 ->
                        Text(text = it1, modifier = Modifier
                            .weight(1f)
                            .padding(8.dp))
                    }
                    ingredient.quantity?.let { quantity ->
                        Text(text = quantity + "g", modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Affichage du poid total
        val totalWeight = selectedRecipe?.ingredients?.sumOf { it.quantity?.toIntOrNull() ?: 0 } ?: 0
        val multiplier = multiplication.intValue
        val finalWeight = totalWeight * multiplier
        Text("Poids total : $finalWeight grammes")


        Spacer(modifier = Modifier.weight(1f, true))

        MyBottomAppBar(navHostController)
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ToolScreenCalcPreview() {
    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ToolScreenCalc(baguetonViewModel = BaguetonViewModel())
        }
    }
}
