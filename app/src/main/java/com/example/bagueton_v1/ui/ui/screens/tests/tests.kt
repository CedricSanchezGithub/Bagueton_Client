package com.example.bagueton_v1.ui.ui.screens.tests
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.WeatherViewModel
import com.example.bagueton_v1.ui.model.Image
import com.example.bagueton_v1.ui.model.RecipeAPI
import com.example.bagueton_v1.ui.model.RecipeBean
import com.example.bagueton_v1.ui.model.WeatherAPI
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.screens.HomeScreen
import previewBaguetonViewModel

fun main(weatherViewModel: WeatherViewModel) {

}

fun mapRecipes(array: List<RecipeBean>, dataWanted: String? = null) {
    println("[[[ Itération sur $dataWanted ]]]")
    if (dataWanted == "title") {
        for(title in array.map { it.title }){
            println(title)
        }
    }  else  if (dataWanted == "ingredients") {
        for(title in array.map { it.ingredients }){
            println(title)
        }
    } else if(dataWanted == "instructions"){
        for(title in array.map { it.steps }){
            println(title)
        }
        } else if(dataWanted == "images"){
        for(title in array.map { it.images?.get(0)?.url }){
            println(title)
        }
    } else {
        for(recipe in array){
            println(recipe)
        }
    }
}

