package com.example.bagueton_v1.ui.theme.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.theme.Bagueton_v1Theme

@Composable
fun RecipesScreen() {

    val searchText = remember { mutableStateOf("") }

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Modifier.fillMaxWidth()

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Bienvenue, Utilisateur", modifier = Modifier.padding(horizontal = 16.dp) )
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            modifier = Modifier,
            searchText = searchText
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Modifier.fillMaxWidth()
            Spacer(Modifier.weight(1f, true))

            Image(
                painter = painterResource(R.drawable.babka),
                contentDescription = "image de babka",
                Modifier.size(150.dp)
            )

            Spacer(Modifier.weight(1f, true))

            Image(
                painter = painterResource(R.drawable.ciabatta),
                contentDescription = "image de babka",
                Modifier.size(150.dp)
            )
            Spacer(Modifier.weight(1f, true))

        }
        Spacer(Modifier.weight(1f, true))

        Row {
            Spacer(Modifier.weight(1f, true))

            Image(
                painter = painterResource(R.drawable.croissant),
                contentDescription = "image de babka",
                Modifier.size(150.dp)
            )
            Spacer(Modifier.weight(1f, true))

            Image(
                painter = painterResource(R.drawable.focaccia),
                contentDescription = "image de babka",
                Modifier.size(150.dp)
            )
            Spacer(Modifier.weight(1f, true))

        }
        Spacer(Modifier.weight(1f, true))

        Row {
            Spacer(Modifier.weight(1f, true))

            Image(
                painter = painterResource(R.drawable.cookie),
                contentDescription = "image de babka",
                Modifier.size(150.dp)
            )
            Spacer(Modifier.weight(1f, true))

            Image(
                painter = painterResource(R.drawable.pain),
                contentDescription = "image de babka",
                Modifier.size(150.dp)
            )
            Spacer(Modifier.weight(1f, true))

        }
        Spacer(Modifier.weight(1f, true))

        MyBottomAppBar()
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecipesScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipesScreen()
        }
    }
}