package com.example.bagueton_v1.ui.model

import com.example.bagueton_v1.R

data class Recipe(val id: Int, val name: String, val imageResId: Int, val recipe: String)


val myRecipe = Recipe(
    id = 1,
    name = "Croissant",
    imageResId = R.drawable.croissant,
    recipe = "Voici comment faire un croissant..."
)


