package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.model.RecipeBean
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.MyBottomAppBar
import com.example.bagueton_v1.ui.ui.SearchBar



@Composable
fun ListRecipeScreen(navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel) {
    Scaffold (
        topBar = {
            SearchBar(baguetonViewModel = baguetonViewModel)
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {  navHostController?.navigate("createrecipescreen")  }) {
                Icon(Icons.Default.Add, contentDescription = "Add",
                    Modifier.background(MaterialTheme.colorScheme.primary))
            }
        }

    ){innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){

            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Modifier.fillMaxWidth()
                ImageGrid(recipes = baguetonViewModel.recipeList)
            }
        }
    }
}

@Composable
fun ImageGrid(recipes: List<RecipeBean>) {
    // Création d'une liste de sous-listes, chaque sous-liste pouvant contenir jusqu'à deux éléments
    val chunkedRecipes = recipes.chunked(2)


    // Affiche une grille de recettes avec des images et des titres superposés avec effet de gradient.
    LazyColumn {
        items(chunkedRecipes.size) { index ->
            val rowRecipes = chunkedRecipes[index]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowRecipes.forEach { recipe ->
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(recipe.image),
                            contentDescription = "Image for ${recipe.title}",
                            modifier = Modifier
                                .fillMaxSize().clickable {  },
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth() // Prend toute la largeur
                                .align(Alignment.BottomCenter)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f)
                                        ),
                                        startY = Float.POSITIVE_INFINITY
                                    )
                                ),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = recipe.title ?: "",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }
                    }
                    if (rowRecipes.size < 2) {
                        Spacer(modifier = Modifier.size(150.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListRecipeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ListRecipeScreen(baguetonViewModel = BaguetonViewModel())
        }
    }
}