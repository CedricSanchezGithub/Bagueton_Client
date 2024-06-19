package com.c3dev.bagueton.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.ui.BaguetonViewModel
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme


@Composable
fun ToolsScreen(
    navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel
) {

    Column {

        SearchBar(baguetonViewModel = baguetonViewModel,welcomeMessage = "Bienvenue, utilisateur",
            navHostController = navHostController
        )
        Row {
            Text(text = "Écran des outils :", modifier = Modifier.padding(horizontal = 16.dp) )
            Text(text = "En construction...", modifier = Modifier.padding(horizontal = 16.dp) )
        }
            Text(
                text = "Calcule recettes",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navHostController?.navigate("ToolScreenCalc")
                }
            )

        Spacer(Modifier.weight(1f, true))
        MyBottomAppBar(navHostController)
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


