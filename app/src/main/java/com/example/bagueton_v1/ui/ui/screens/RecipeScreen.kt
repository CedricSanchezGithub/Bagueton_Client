package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
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
fun RecipeScreen(id_recipe: Long?,
                 navHostController: NavHostController? = null,
                 baguetonViewModel: BaguetonViewModel,
) {
    Scaffold (
        topBar = {
            SearchBar(baguetonViewModel = baguetonViewModel, welcomeMessage = null)
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController,
            )
        }){innerPadding ->
        val recipe = baguetonViewModel.recipeList.find { it.id_recipe == id_recipe }
        val scrollState = rememberScrollState()

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(innerPadding)
                .verticalScroll(scrollState),) {
            if (recipe != null) {
                HeaderRecipeScreen(baguetonViewModel, recipe)
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (recipe != null) {
                BodyRecipeScreen(baguetonViewModel, id_recipe, recipe)
            }
        }
    }
}

@Composable
fun HeaderRecipeScreen(baguetonViewModel: BaguetonViewModel, recipe: RecipeBean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        Box(modifier = Modifier.fillMaxWidth()) {

            recipe.let {
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = screenHeight / 2)
                        .clip(RoundedCornerShape(0.dp, 8.dp, 20.dp, 20.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = it.title.orEmpty(),
                    fontSize = 40.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                ),
                                startY = Float.POSITIVE_INFINITY
                            )
                        ),
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 8f
                        )
                    )
                )
            }
        }
    }
}



@Composable
fun BodyRecipeScreen(baguetonViewModel: BaguetonViewModel, id_recipe: Long?, recipe: RecipeBean){


    Column {

        recipe.ingredients?.let {
            Text(text = it.replace(", ", "\n"),
                textAlign = TextAlign.Center,

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )}

        Spacer(modifier = Modifier.height(16.dp))

        recipe.steps?.let {
            Text(text = it.replace(", ", "\n"),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable

fun RecipeScreenPreview() {
    // Création d'un RecipeBean factice
    fun mockRecipe() = RecipeBean(
        id_recipe = 1L,
        title = "Pain à l'ancienne",
        ingredients = "Farine, Eau, Levure, Sel",
        image = "http://90.51.140.217:8081/campagne.jpg", // Utilisez une URL d'image accessible
        steps = "1. Mélanger tous les ingrédients, 2. Pétrir la pâte, 3. Laisser reposer la pâte, 4. Façonner et cuire à 240°C pendant 30 minutes."
    )


    // Création d'un BaguetonViewModel factice
    fun mockBaguetonViewModel() = BaguetonViewModel().apply {
        // Ajouter la recette factice à la liste des recettes
        recipeList.add(mockRecipe())
    }
    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipeScreen(
                id_recipe = 1L,  // ID de la recette factice
                baguetonViewModel = mockBaguetonViewModel()
            )
        }
    }
}