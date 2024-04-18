package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.ConfirmationSnackbar
import com.example.bagueton_v1.ui.ui.MyBottomAppBar
import com.example.bagueton_v1.ui.ui.SearchBar


@Composable
fun CreateRecipeScreen(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel, ) {

    // snakebar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ){innerPadding ->


        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nouvelle recette")
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = baguetonViewModel.titleRecipe.value,
                onValueChange = { baguetonViewModel.titleRecipe.value = it },
                label = { Text("Entrez le nom de votre recette") }
            )
            OutlinedTextField(
                value = baguetonViewModel.ingredientsRecipe.value,
                onValueChange = { baguetonViewModel.ingredientsRecipe.value = it },
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
                value = baguetonViewModel.stepsRecipe.value,
                onValueChange = { baguetonViewModel.stepsRecipe.value = it },
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

            Button(onClick = { baguetonViewModel.createRecipe(title = baguetonViewModel.titleRecipe.value,
                ingredient = baguetonViewModel.ingredientsRecipe.value,
                steps = baguetonViewModel.stepsRecipe.value)


            },
                modifier = Modifier
                    .height(40.dp) // Hauteur personnalisée
                    .padding(horizontal = 16.dp) // Padding horizontal

            ) {
                Text(text = "Créer ma recette")
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
fun CreateRecipeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CreateRecipeScreen(baguetonViewModel = BaguetonViewModel())
        }
    }
}

