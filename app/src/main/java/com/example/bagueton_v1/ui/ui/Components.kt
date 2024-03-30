package com.example.bagueton_v1.ui.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.screens.RecipesScreen
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Header(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        Box(modifier = Modifier
            .fillMaxWidth()) { // Ajoute un padding autour de la Box, si nécessaire
            SearchBar(modifier = Modifier)
            Image(
                painter = painterResource(R.drawable.croissant),
                contentDescription = "Croissants",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .heightIn(max = screenHeight / 2), // Applique un arrondi aux coins de l'Image
                contentScale = ContentScale.Crop // Ajuste le remplissage de l'image

            )

            Text(
                text = "Croissants",
                fontSize = 40.sp,
                modifier = Modifier
                    .align(Alignment.Center) // Centre le texte dans la Box
                    .padding(16.dp), // Ajoute un padding au texte, si nécessaire
                color = Color.White, // Assurez-vous que la couleur du texte se démarque sur l'image
                style = TextStyle(shadow = Shadow( // Optionnel : Ajoute une ombre pour améliorer la lisibilité sur des images variées
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f)
                )
            )
        }

        Row {
            Image(
                painter = painterResource(R.drawable.croissant2),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop)

            Image(
                painter = painterResource(R.drawable.croissant3),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop)

            Image(
                painter = painterResource(R.drawable.croissant4),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop)

            Image(
                painter = painterResource(R.drawable.component_add_picture),
                contentDescription = "composant ajouter image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop)
        }
    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier, searchText: MutableState<String>? = null) {


    if (searchText != null) {
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
                    IconButton(onClick = {
                        searchText.value = ""
                    }) {
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
}
@Composable
fun MyBottomAppBar(navHostController: NavHostController? = null) {
    BottomAppBar(
        // Personnalisez l'apparence de votre BottomAppBar ici
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,

        ) {
        IconButton(onClick = { navHostController?.navigate("HomeScreen") }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Build, contentDescription = "Accueil",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoris",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Paramètres",
                Modifier.size(40.dp))
        }

    }
}
@Composable
fun PreviewInfos(){
    Row {
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil",
                Modifier.size(40.dp))
        }
    }

}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewMyBottomAppBar() {
    MyBottomAppBar()
}
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HeaderPreview() {
    Header()
}
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchBarPreview() {
    SearchBar()
}
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewInfosPreview() {
    PreviewInfos()
}
