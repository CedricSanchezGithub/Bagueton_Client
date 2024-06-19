package com.c3dev.bagueton.ui.ui.screens.tests
import com.c3dev.bagueton.ui.model.RecipeBean


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

