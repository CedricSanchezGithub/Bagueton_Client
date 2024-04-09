package com.example.bagueton_v1.ui.ui.screens
/*
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.model.ingredients
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.Header
import com.example.bagueton_v1.ui.ui.MyBottomAppBar

@Composable
fun RecipeScreen2(navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(baguetonViewModel)
        Spacer(Modifier.weight(1f, true))
        IngredientsList()
        Spacer(Modifier.weight(1f, true))
        MyBottomAppBar()    }
}
@Composable
fun IngredientsList() {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(ingredients) { ingredient ->
            Text(text = "${ingredient.name}: ${ingredient.quantity}", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IngredientsListPreview() {

    Bagueton_v1Theme {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipeScreen2(baguetonViewModel = BaguetonViewModel())
        }
    }
}
*/