package com.c3dev.bagueton.ui.ui.screens.recipes
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.c3dev.bagueton.ui.model.beans.RecipeBean
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.SearchBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme


@Composable
fun HomeScreen(baguetonViewModel: BaguetonViewModel,
               navHostController: NavHostController? = null)
{
    // Accéder à la variable errorMessage dans l'instance de BaguetonViewModel
    val errorMessage by baguetonViewModel.errorMessage
    // LaunchedEffect est utilisé pour charger les recettes au démarrage de la composable
    LaunchedEffect(key1 = true) {
        baguetonViewModel.loadRecipes()
    }

    Scaffold(
        topBar = {
            // Passer l'instance de BaguetonViewModel à SearchBar
            SearchBar(baguetonViewModel = baguetonViewModel)
        },
        bottomBar = {
            // Passer l'instance de NavHostController à MyBottomAppBar
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
                if(errorMessage === ""){

                    Text(text = "Liste des commandes :",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textDecoration = TextDecoration.Underline,
                    )
                } else {
                    Text(text = baguetonViewModel.errorMessage.value,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
            // Prend uniquement les 5 premiers éléments de la liste pour l'affichage
            val recipeList = baguetonViewModel.recipeList.takeLast(5)
            // Carrousel pour afficher les recettes
            LazyRow(modifier = Modifier.padding(16.dp)) {
                items(recipeList) { recipe ->
                    RecipeImage(recipe = recipe, navHostController = navHostController)
                }
            }

            Button(onClick = {  navHostController?.navigate("CalendarScreen")  },
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

            LazyRow(modifier = Modifier.padding(16.dp)) {
                items(recipeList) { recipe ->
                    RecipeImage(recipe = recipe, navHostController = navHostController)
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

@Composable
fun RecipeImage(
    recipe: RecipeBean,
    navHostController: NavHostController?
) {
    val imageUrl = recipe.images?.firstOrNull()?.url
    val imageContent =
        @Composable {
        if (!imageUrl.isNullOrEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data("$imageUrl")
                        .crossfade(true)
                        .build()
                ),
                contentDescription = "Image de ${recipe.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.shadow(elevation = 8.dp,
                shape = RoundedCornerShape(16.dp, 32.dp, 16.dp, 32.dp),
                clip = true
            )
            )
        } else {
            Text(
                text = "Image non disponible",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }

    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(10.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(16.dp, 32.dp, 16.dp, 32.dp)
            )
            .clip(RoundedCornerShape(16.dp, 32.dp, 16.dp, 32.dp))
            .clickable { navHostController?.navigate(route = "RecipeScreen/${recipe.id}") }
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        imageContent()
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            HomeScreen( baguetonViewModel = BaguetonViewModel())
        }
    }
}


