package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.MyBottomAppBar
import com.example.bagueton_v1.ui.ui.SearchBar


@Composable
fun ToolsScreen(
    navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel
) {

    Column {

        SearchBar(modifier = Modifier, baguetonViewModel = BaguetonViewModel())

        Row {
            Text(text = "Écran des outils :", modifier = Modifier.padding(horizontal = 16.dp) )
            Text(text = "En construction...", modifier = Modifier.padding(horizontal = 16.dp) )
        }

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


