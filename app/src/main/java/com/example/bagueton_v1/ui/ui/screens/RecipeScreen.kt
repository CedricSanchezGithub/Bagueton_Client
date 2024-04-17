package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import android.text.TextUtils.replace
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding),) {
            if (recipe != null) {
                Header(baguetonViewModel, id_recipe, recipe)
            }
            if (recipe != null) {
                BodyRecipeScreen(baguetonViewModel, id_recipe, recipe)
            }
        }
    }
}

@Composable
fun Header(baguetonViewModel: BaguetonViewModel, id_recipe: Long?, recipe: RecipeBean) {
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
                        .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                        .heightIn(max = screenHeight / 2),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = it.title.orEmpty(),
                    fontSize = 40.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
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

        recipe.ingredients?.let { Text(text = it.replace(", ", "\n")) }

    }


}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {

    Bagueton_v1Theme {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipeScreen(baguetonViewModel = BaguetonViewModel(), id_recipe = 1)
        }
    }
}