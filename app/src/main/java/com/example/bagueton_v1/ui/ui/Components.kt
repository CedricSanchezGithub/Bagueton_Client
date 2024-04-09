package com.example.bagueton_v1.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.BaguetonViewModel

@Composable
fun Header(baguetonViewModel: BaguetonViewModel, id :Long?){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val recipe = baguetonViewModel.recipeList.find { it.id_recipe == id }

        Box(modifier = Modifier
            .fillMaxWidth()) {
            SearchBar(modifier = Modifier, baguetonViewModel = BaguetonViewModel())
            if (recipe != null) {
                Image(
                    painter = rememberAsyncImagePainter(recipe.image),
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                        .heightIn(max = screenHeight / 2),
                    contentScale = ContentScale.Crop

                )
            }

            if (recipe != null) {
                recipe.title?.let {
                    Text(
                        text = it,
                        fontSize = 40.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        color = Color.White,
                        style = TextStyle(shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 8f)
                        )
                    )
                }
            }
        }

       /* Row {
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
        }*/
    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier, searchText: String? = null, baguetonViewModel: BaguetonViewModel) {

    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Bienvenue, Utilisateur", modifier = Modifier.padding(horizontal = 16.dp) )
    Spacer(modifier = Modifier.height(16.dp))

    if (searchText != null) {
        TextField(
            value = baguetonViewModel.searchText.value,
            onValueChange = { baguetonViewModel.searchText.value = it },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (baguetonViewModel.searchText.value.isNotEmpty()) {
                    IconButton(onClick = {
                        baguetonViewModel.searchText.value = ""
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
        IconButton(onClick = { navHostController?.navigate("ToolsScreen") }) {
            Icon(Icons.Filled.Build, contentDescription = "Outils",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("FavoriteScreen") }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoris",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("LoginScreen")  }) {
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
    Header(baguetonViewModel = BaguetonViewModel(), null)
}
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchBarPreview() {
    SearchBar(baguetonViewModel = BaguetonViewModel())
}
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewInfosPreview() {
    PreviewInfos()
}
