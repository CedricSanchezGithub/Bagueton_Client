package com.c3dev.bagueton.ui.ui.screens.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.model.beans.Ingredient
import com.c3dev.bagueton.ui.model.beans.Step
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@Composable
fun CreateRecipeScreen(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel
) {

    LaunchedEffect(key1 = true) {
        baguetonViewModel.clearRecipe()
    }

    Scaffold(
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(scrollState)

        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.new_recipe))
            Spacer(modifier = Modifier.height(16.dp))

            TitleInput(
                title = baguetonViewModel.titleRecipe.value,
                onTitleChange = { newTitle ->
                    baguetonViewModel.titleRecipe.value = newTitle
                },
            )
            Card(
                modifier = Modifier.padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.ingredient),
                        modifier = Modifier.padding(16.dp).width(300.dp), textAlign = TextAlign.Center)
                    for ((index, ingredient) in baguetonViewModel.ingredientsList.withIndex()) {
                        IngredientInput(
                            ingredient = ingredient,
                            onIngredientChange = { newIngredient ->
                                baguetonViewModel.updateIngredient(index, newIngredient, ingredient.quantity ?: "")
                            },
                            onQuantityChange = { newQuantity ->
                                val filteredQuantity = newQuantity.filter { it.isDigit() }
                                baguetonViewModel.updateIngredient(index, ingredient.ingredient ?: "", filteredQuantity)
                            },
                            onDelete = {
                                baguetonViewModel.ingredientsList.removeAt(index)
                            }
                        )
                    }
                    Button(onClick = { baguetonViewModel.addIngredient() },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(stringResource(id = R.string.add_ingredient))
                    }
                }
            }

            Card(
                modifier = Modifier.padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.step_recipe),
                        modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)

                    for ((index, step) in baguetonViewModel.stepsList.withIndex()) {
                        StepInput(
                            step = step,
                            onStepChange = { newStep ->
                                println("Updating step at index $index to $newStep")
                                baguetonViewModel.updateStep(index, newStep)
                            },
                            onDelete = {
                                println("Deleting step at index $index")
                                baguetonViewModel.stepsList.removeAt(index)
                            }
                        )
                    }
                    Button(
                        onClick = { baguetonViewModel.addStep() },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(stringResource(id = R.string.add_step))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                println("Données de stepsList : ${baguetonViewModel.stepsList.map { it.description }}")
                baguetonViewModel.createRecipe(
                    title = baguetonViewModel.titleRecipe.value,
                    ingredients = baguetonViewModel.ingredientsList,
                    steps = baguetonViewModel.stepsList
                )
            }) {
                Text(stringResource(id = R.string.send_recipe))
            }
            Text(
                text = "Code de retour: ${baguetonViewModel.httpCode.intValue}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun TitleInput(
    title: String,
    onTitleChange: (String) -> Unit,
) {
    var isTitleFocused by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ){
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .width(300.dp)
                .background(Color.White)
                .onFocusChanged { focusState ->
                    isTitleFocused = focusState.isFocused
                }
        ) {
            if (title.isEmpty() && !isTitleFocused) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.title_recipe),
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            BasicTextField(
                value = title,
                onValueChange = onTitleChange,
                singleLine = true,
                modifier = Modifier
                    .width(300.dp)
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun IngredientInput(
    ingredient: Ingredient,
    onIngredientChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onDelete: () -> Unit
) {
    var isIngredientFocused by remember { mutableStateOf(false) }
    var isQuantityFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp) // Ajouter un padding global à la colonne
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .width(300.dp)
                .onFocusChanged { focusState ->
                    isIngredientFocused = focusState.isFocused
                }
        ) {
            if (ingredient.ingredient!!.isEmpty() && !isIngredientFocused) {
                Text(
                    text = stringResource(id = R.string.ingredient),
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = ingredient.ingredient ?: "",
                onValueChange = {
                    println("Ingredient changed: $it")
                    onIngredientChange(it)
                },
                singleLine = true,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp)) // Ajouter un espace entre les champs

        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .width(300.dp)
                .onFocusChanged { focusState ->
                    isQuantityFocused = focusState.isFocused
                }
        ) {
            if (ingredient.quantity!!.isEmpty() && !isQuantityFocused) {
                Text(
                    text = stringResource(id = R.string.quantity),
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = ingredient.quantity ?: "",
                onValueChange = {
                    val filteredQuantity = it.filter { it.isDigit() }
                    println("Quantity changed: $filteredQuantity")
                    onQuantityChange(filteredQuantity)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp)) // Ajouter un espace entre le champ et le bouton

        Button(onClick = onDelete) {
            Text(text = "Supprimer")
        }
    }
}

@Composable
fun StepInput(
    step: Step,
    onStepChange: (String) -> Unit,
    onDelete: () -> Unit
) {
    var isStepFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .width(300.dp)
                .onFocusChanged { focusState ->
                    isStepFocused = focusState.isFocused
                }
        ) {
            if (step.description!!.isEmpty() && !isStepFocused) {
                Text(
                    text = stringResource(id = R.string.step),
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = step.description ?: "",
                onValueChange = {
                    onStepChange(it)
                },
                singleLine = true,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onDelete) {
            Text(text = "Supprimer")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateRecipeScreenPreview() {
    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CreateRecipeScreen(baguetonViewModel = BaguetonViewModel())
        }
    }
}
