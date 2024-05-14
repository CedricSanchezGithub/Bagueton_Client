package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.geometry.Offset
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
import com.example.bagueton_v1.ui.previewBaguetonViewModel
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.MyBottomAppBar
import com.example.bagueton_v1.ui.ui.SearchBar

@Composable
fun RecipeScreen(id: String?,
                 navHostController: NavHostController? = null,
                 baguetonViewModel: BaguetonViewModel,
) {
    Scaffold (
        topBar = {
            SearchBar(baguetonViewModel = baguetonViewModel,welcomeMessage = "Bienvenue, utilisateur",
                navHostController = navHostController
            )
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController,
            )

        },
        floatingActionButton = {
            FloatingActionButton(

                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {  navHostController?.navigate("UpdateRecipeScreenRecipeScreen.kt/${id}")  }) {
                (Icon(
                    Icons.Filled.Edit, "Extended floating action button.",
                    Modifier.background(MaterialTheme.colorScheme.primary)))
            }
        },)
    {innerPadding ->
        val recipe = baguetonViewModel.recipeList.find { it.idRecipe == id }
        val scrollState = rememberScrollState()

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(innerPadding)
                .fillMaxHeight()
                .verticalScroll(scrollState),) {
            if (recipe != null) {

                HeaderRecipeScreen(baguetonViewModel, recipe)
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (recipe != null) {
                BodyRecipeScreen(recipe)
            }
        }
    }
}

@Composable
fun HeaderRecipeScreen(baguetonViewModel: BaguetonViewModel, recipe: RecipeBean) {
    Modifier.padding(16.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        Box()
        {
            recipe.let {
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = screenHeight / 3)
                        .padding(8.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50.dp, 160.dp, 50.dp, 160.dp))
                        .clip(RoundedCornerShape(50.dp, 160.dp, 50.dp, 160.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = it.title.orEmpty(),
                    fontSize = 30.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(32.dp)
                        .fillMaxWidth()
                        .height(30.dp),
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
fun BodyRecipeScreen(recipe: RecipeBean){


    Column(Modifier.padding(32.dp)) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                .align(Alignment.CenterHorizontally)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                .padding(32.dp)

        ) {
            recipe.ingredients?.let {
                Text(
                    text = it.replace(", ", "\n"),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(Alignment.Center)  // Centrer le texte dans la Box
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        recipe.steps?.let {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                    .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                    .align(Alignment.CenterHorizontally)
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                    .padding(32.dp)


            ) {
                Text(
                    text = it.replace(", ", "\n"),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)  // Centrer le texte dans la Box
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable

fun RecipeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

            RecipeScreen(  // ID de la recette factice
                baguetonViewModel = previewBaguetonViewModel(),
                id = "3000"
            )
        }
    }
}