package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.Header
import com.example.bagueton_v1.ui.ui.MyBottomAppBar

@Composable
fun RecipeScreen(id: Long?, navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(baguetonViewModel = BaguetonViewModel(), id)
        Spacer(Modifier.weight(1f, true))
        MyBottomAppBar()
    }
}




@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {

    Bagueton_v1Theme {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipeScreen(baguetonViewModel = BaguetonViewModel(), id = 1)
        }
    }
}