package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.model.RecipeAPI.deleteRecipe
import com.example.bagueton_v1.ui.previewBaguetonViewModel
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.ConfirmationSnackbar
import com.example.bagueton_v1.ui.ui.MyBottomAppBar


@Composable
fun UpdateRecipeScreen(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel,
    id : String ? = null) {

    // snakebar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val recipe = baguetonViewModel.recipeList.find { it.idRecipe == id }


    Scaffold (
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ){innerPadding ->

        Text(text = if (recipe != null) "Modifier la recette ${recipe.title}" else "Recette non trouvée",
            textAlign = TextAlign.Center)

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)) {


            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = baguetonViewModel.newTitleRecipe.value,
                onValueChange = { baguetonViewModel.newTitleRecipe.value = it },
                label = {
                    if (recipe != null) { Text("${recipe.title}") }
                }
            )
            OutlinedTextField(
                value = baguetonViewModel.newIngredientsRecipe.value,
                onValueChange = { baguetonViewModel.newIngredientsRecipe.value = it },
                label = { Text("Entrez vos ingrédients, séparés par une virgule*") },
                modifier = Modifier
                    .fillMaxWidth() // Remplit la largeur maximale disponible.
                    .height(150.dp) // Définit une hauteur fixe pour le champ de texte
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences, // Commence les phrases par des majuscules.
                    keyboardType = KeyboardType.Text, // Type de clavier adapté à la saisie de texte.
                    imeAction = ImeAction.Default
                ),
                maxLines = 5, // Augmente le nombre de lignes pour permettre la saisie de paragraphes.
                textStyle = TextStyle(textAlign = TextAlign.Start) // Alignement du texte à gauche.
            )

            OutlinedTextField(
                value = baguetonViewModel.newStepsRecipe.value,
                onValueChange = { baguetonViewModel.newStepsRecipe.value = it },
                label = { Text("Entrez vos étapes de préparation, séparés par une virgule*") },
                modifier = Modifier
                    .fillMaxWidth() // Remplit la largeur maximale disponible.
                    .height(150.dp) // Définit une hauteur fixe pour le champ de texte
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences, // Commence les phrases par des majuscules.
                    keyboardType = KeyboardType.Text, // Type de clavier adapté à la saisie de texte.
                    imeAction = ImeAction.Default
                ),
                maxLines = 5, // Augmente le nombre de lignes pour permettre la saisie de paragraphes.
                textStyle = TextStyle(textAlign = TextAlign.Start) // Alignement du texte à gauche.
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row {


                Button(
                    onClick = {
                        if (id != null) {
                            baguetonViewModel.updateRecipe(
                                id = id,
                                title = baguetonViewModel.newTitleRecipe.value,
                                ingredient = baguetonViewModel.newIngredientsRecipe.value,
                                steps = baguetonViewModel.newStepsRecipe.value
                            )
                        }


                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(horizontal = 16.dp),
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,  // Custom background color
                    )

                ) {
                    Icon(imageVector = Icons.Filled.EditNote, contentDescription = "modifier")
                }
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {deleteRecipe(id)},
                    modifier = Modifier
                        .height(40.dp) // Hauteur personnalisée
                        .padding(horizontal = 16.dp),
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,  // Custom background color
                    )

                ) {
                    Icon(imageVector = Icons.Filled.DeleteForever, contentDescription = "delete")
                }

            }
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "* Permet un meilleur affichage de la recette. \nUne virgule = un saut de ligne.")
            if (baguetonViewModel.snackBarValue.value){
                ConfirmationSnackbar(snackbarHostState = snackbarHostState, scope = scope, message = "Recette ajoutée avec succès !", BaguetonViewModel())
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun UpdateRecipeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            UpdateRecipeScreen(baguetonViewModel = previewBaguetonViewModel(),  id = "4000")
        }
    }
}

