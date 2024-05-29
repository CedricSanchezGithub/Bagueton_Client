package com.example.bagueton_v1.ui.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.model.Ingredient
import com.example.bagueton_v1.ui.model.Step
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.MyBottomAppBar

@Composable
fun CreateRecipeScreen(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel
) {
    Scaffold(
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nouvelle recette")
            Spacer(modifier = Modifier.height(16.dp))

            // Champ de saisie pour le titre
            TitleInput(
                title = baguetonViewModel.titleRecipe.value,
                onTitleChange = { newTitle ->
                    baguetonViewModel.titleRecipe.value = newTitle
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Ingrédients")
            for ((index, ingredient) in baguetonViewModel.ingredientsList.withIndex()) {
                IngredientInput(
                    ingredient = ingredient,
                    onIngredientChange = { newIngredient ->
                        baguetonViewModel.updateIngredient(index, newIngredient, ingredient.quantity ?: "")
                    },
                    onQuantityChange = { newQuantity ->
                        val filteredQuantity = newQuantity.filter { it.isDigit() }
                        baguetonViewModel.updateIngredient(index, ingredient.ingredient ?: "", filteredQuantity)
                    }
                )
            }
            Button(onClick = { baguetonViewModel.addIngredient() }) {
                Text("Ajouter un ingrédient")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Étapes de préparation")
            for ((index, step) in baguetonViewModel.stepsList.withIndex()) {
                StepInput(
                    step = step,
                    onStepChange = { newStep ->
                        baguetonViewModel.updateStep(index, newStep)
                    }
                )
            }
            Button(onClick = { baguetonViewModel.addStep() }) {
                Text("Ajouter une étape")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                baguetonViewModel.createRecipe(
                    title = baguetonViewModel.titleRecipe.value,
                    ingredients = baguetonViewModel.ingredientsList,
                    steps = baguetonViewModel.stepsList
                )
            }) {
                Text("Enregistrer la recette")
            }
        }
    }
}

@Composable
fun TitleInput(
    title: String,
    onTitleChange: (String) -> Unit
) {
    var isTitleFocused by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .border(1.dp, Color.Gray)
                .width(300.dp)
                .onFocusChanged { focusState ->
                    isTitleFocused = focusState.isFocused
                }
        ) {
            if (title.isEmpty() && !isTitleFocused) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Titre de la recette",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            BasicTextField(
                value = title,
                onValueChange = onTitleChange,
                singleLine = true,
                modifier = Modifier.width(300.dp)
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
    onQuantityChange: (String) -> Unit
) {
    var isIngredientFocused by remember { mutableStateOf(false) }
    var isQuantityFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isIngredientFocused = focusState.isFocused
                }
        ) {
            if (ingredient.ingredient!!.isEmpty() && !isIngredientFocused) {
                Text(
                    text = "Ingrédient",
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = ingredient.ingredient ?: "",
                onValueChange = onIngredientChange,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isQuantityFocused = focusState.isFocused
                }
        ) {
            if (ingredient.quantity!!.isEmpty() && !isQuantityFocused) {
                Text(
                    text = "Quantité",
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = ingredient.quantity ?: "",
                onValueChange = onQuantityChange,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun StepInput(
    step: Step,
    onStepChange: (String) -> Unit
) {
    var isStepFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isStepFocused = focusState.isFocused
                }
        ) {
            if (step.description!!.isEmpty() && !isStepFocused) {
                Text(
                    text = "Étape",
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
            BasicTextField(
                value = step.description ?: "",
                onValueChange = onStepChange,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
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
