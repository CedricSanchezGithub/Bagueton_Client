package com.example.bagueton_v1.ui.ui.screens
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.MyBottomAppBar
import com.example.bagueton_v1.ui.ui.SearchBar


@Composable
fun HomeScreen(
    navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel) {

    LaunchedEffect(key1 = true) {
        baguetonViewModel.loadRecipes()
    }

    Scaffold (
        topBar = {
            SearchBar(baguetonViewModel = baguetonViewModel)
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }

    ){innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Row {
                Text(text = "Liste des commandes :",
                    modifier = Modifier.padding(horizontal = 16.dp),
                            textDecoration = TextDecoration.Underline,
                )
            }
            LazyRow {
                // Prend uniquement les 5 premiers éléments de la liste pour l'affichage
                val imagesToShow = baguetonViewModel.recipeList.take(5)
                items(imagesToShow) { recipe ->
                    Image(
                        painter = rememberAsyncImagePainter(recipe.image),
                        contentDescription = "Image de ${recipe.title}",
                        modifier = Modifier
                            .clickable {  run { navHostController?.navigate("RecipeScreen/${recipe.id_recipe}") } }
                            .size(150.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxSize(),

                        contentScale = ContentScale.Crop,


                    )
                }
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Agenda")

            }
            Spacer(modifier = Modifier.weight(1f))

            Text(text = "Vos recettes les plus utilisées :",
                modifier = Modifier.padding(horizontal = 16.dp),
                textDecoration = TextDecoration.Underline,
            )

            LazyRow {
                // Prend uniquement les 3 premiers éléments de la liste pour l'affichage
                val imagesToShow = baguetonViewModel.recipeList.takeLast(4)
                items(imagesToShow) { recipe ->
                    Image(
                        painter = rememberAsyncImagePainter(model = recipe.image),
                        contentDescription = "Image de ${recipe.title}",
                        modifier = Modifier
                            .clickable {  run { navHostController?.navigate("RecipeScreen/${recipe.id_recipe}") } }
                            .size(150.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Button(onClick = { navHostController?.navigate("ListRecipeScreen") },
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Recettes")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            HomeScreen(baguetonViewModel = BaguetonViewModel())
        }
    }
}


