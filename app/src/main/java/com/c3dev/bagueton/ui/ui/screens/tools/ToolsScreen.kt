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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R
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

            RecipeCalcCard(navHostController)
            AgendaCard()
            ProofingChamberCard()

        }
    }
}

@Composable
fun RecipeCalcCard(navHostController: NavHostController? = null) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.card_minimal_example_title))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(id = R.string.card_minimal_example_description)
            )
            Button(
                onClick = { navHostController?.navigate("ToolScreenCalc") },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(stringResource(id = R.string.card_minimal_example_button))
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
            Text(text = stringResource(id = R.string.agenda_card_title))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = R.string.agenda_card_message))

            Button(
                onClick = {
                    // En construction...
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(stringResource(id = R.string.agenda_card_button))
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
            Text(text = stringResource(id = R.string.proofing_chamber_title))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(id = R.string.proofing_chamber_message))

            Button(
                onClick = {
                    // En construction...
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(stringResource(id = R.string.proofing_chamber_button))
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


