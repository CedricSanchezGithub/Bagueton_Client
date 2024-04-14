package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.bagueton_v1.ui.ui.MyBottomAppBar
import com.example.bagueton_v1.ui.ui.SearchBar


fun main() {



}
@Composable
fun CreateRecipeScreen(
    navHostController: NavHostController? = null,
    baguetonViewModel: BaguetonViewModel) {
    val showAlert = remember { mutableStateOf(false) }


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)) {
        SearchBar(modifier = Modifier, baguetonViewModel = BaguetonViewModel())
        Text(text = "Créez votre recette")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = baguetonViewModel.titleRecipe.value,
            onValueChange = { baguetonViewModel.titleRecipe.value = it },
            label = { Text("Entrez le nom de votre recette") }
        )
        OutlinedTextField(
            value = baguetonViewModel.ingredientsRecipe.value,
            onValueChange = { baguetonViewModel.ingredientsRecipe.value = it },
            label = { Text("Entrez vos ingrédients, séparés par une virgule") },
            modifier = Modifier
                .fillMaxWidth() // Remplit la largeur maximale disponible.
                .height(150.dp) // Définit une hauteur fixe pour le champ de texte
                .padding(20.dp),
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
            label = { Text("Entrez vos étapes de préparation") },
            modifier = Modifier
                .fillMaxWidth() // Remplit la largeur maximale disponible.
                .height(150.dp) // Définit une hauteur fixe pour le champ de texte
                .padding(20.dp),
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
        if (showAlert.value) {
            if (navHostController != null) {
                ConfirmationAlertDialog(showAlert = showAlert, navHostController)
            }
        }
        Spacer(Modifier.weight(1f, true))
        MyBottomAppBar(navHostController)
    }
}



@Composable
fun ConfirmationAlertDialog(showAlert: MutableState<Boolean>, navHostController: NavHostController) {
    AlertDialog(
        onDismissRequest = {
            // Fermer l'alerte si l'utilisateur touche en dehors de l'alerte
            showAlert.value = false
        },
        title = {
            Text(text = "Confirmation")
        },
        text = {
            Text("La recette a été créée avec succès.")
        },
        confirmButton = {
            Button(onClick = {
                showAlert.value = false // Fermer l'alerte
                // Optionnel: naviguer vers l'écran d'accueil ou effectuer une autre action
                navHostController.navigate("HomeScreen")
            }) {
                Text("OK")
            }
        }
    )
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

