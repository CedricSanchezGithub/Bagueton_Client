package com.c3dev.bagueton.ui.ui.screens.tools

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.screens.recipes.BaguetonViewModel
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme


@Composable
fun ToolsScreen(
    navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel
) {
    Scaffold(
        bottomBar = { MyBottomAppBar(navHostController = navHostController) },
        topBar = { SearchBar(baguetonViewModel = baguetonViewModel, navHostController = navHostController) },
    ){ innerPadding ->


        Column(modifier = Modifier.padding(innerPadding)) {

            CardMinimalExample(navHostController)
            AgendaCard()
            ProofingChamberCard()

        }
    }
}

@Composable
fun CardMinimalExample(navHostController: NavHostController? = null) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Écran de calcul de recette")
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Sur cet écran, vous pouvez calculer les ingrédients de votre recette soit en utilisant un multiplicateur de recette, soit en spécifiant le poids final souhaité du produit."
            )
            Button(
                onClick = { navHostController?.navigate("ToolScreenCalc") },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Aller au calculateur de recettes")
            }
        }
    }
}

@Composable
fun AgendaCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Ecran d'Agenda & Commandes")
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Cette fonctionnalité est en construction...")

            Button(
                onClick = {
                    // Ajoutez la navigation ou l'action appropriée ici si nécessaire
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Agenda")
            }
        }
    }
}

@Composable
fun ProofingChamberCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Calcul de Chambre de Pousse")
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Cette fonctionnalité est en construction...")

            Button(
                onClick = {
                    // Ajoutez la navigation ou l'action appropriée ici si nécessaire
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Calcul de Chambre de Pousse")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ToolsScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ToolsScreen(baguetonViewModel = BaguetonViewModel())
        }
    }
}


