package com.example.bagueton_v1.ui.screens
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController? = null) {

    val searchText = remember { mutableStateOf("") }
    Column {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Bienvenue, Utilisateur", modifier = Modifier.padding(horizontal = 16.dp) )
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            modifier = Modifier,
            searchText = searchText
        )
        Spacer(Modifier.weight(1f, true))

        Row {
            Text(text = "Liste des commandes :", modifier = Modifier.padding(horizontal = 16.dp) )

        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            // Supposons que vous avez plusieurs images, vous pouvez les ajouter ici
            items(listOf(R.drawable.babka, R.drawable.croissant, R.drawable.cookie)) { image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Image de cookie",
                    modifier = Modifier.size(150.dp) // Définit la taille de chaque image
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .height(40.dp) // Hauteur personnalisée
                .padding(horizontal = 16.dp) // Padding horizontal
        ) {
            Text(text = "Agenda")

        }
        Spacer(Modifier.weight(1f, true))

        Text(text = "Vos recettes les plus utilisées :", modifier = Modifier.padding(horizontal = 16.dp) )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow {
            // Supposons que vous avez plusieurs images, vous pouvez les ajouter ici
            items(listOf(R.drawable.babka, R.drawable.croissant, R.drawable.cookie, R.drawable.croissant, R.drawable.ciabatta, R.drawable.viennois)) { image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Image de cookie",
                    modifier = Modifier.size(150.dp) // Définit la taille de chaque image
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .height(40.dp) // Hauteur personnalisée
                .padding(horizontal = 16.dp) // Padding horizontal
        ) {
            Text(text = "Recettes")
        }
        Row {
        }
        Spacer(Modifier.weight(1f, true))
        MyBottomAppBar()
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            HomeScreen()
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, searchText: MutableState<String>) {


    TextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (searchText.value.isNotEmpty()) {
                IconButton(onClick = { searchText.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Effacer"
                    )
                }
            }
        },
        placeholder = { Text("Rechercher") },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

