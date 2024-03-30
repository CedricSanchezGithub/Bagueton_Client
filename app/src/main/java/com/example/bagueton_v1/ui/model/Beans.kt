package com.example.bagueton_v1.ui.model

import com.example.bagueton_v1.R

data class Ingredient(val name: String, val quantity: String)

data class Recipe(
    val id: Int,
    val name: String,
    val steps: String,
    val imageResId: Int,
    val ingredients: List<Ingredient>
)

    // Création des ingrédients pour la recette de croissant
    val ingredients = listOf(
        Ingredient("Farine", "500g"),
        Ingredient("Eau", "140ml"),
        Ingredient("Lait", "60ml"),
        Ingredient("Sucre", "50g"),
        Ingredient("Beurre doux", "300g pour le tourage"),
        Ingredient("Levure fraîche", "15g"),
        Ingredient("Sel", "10g"),
        Ingredient("Beurre pour la pâte", "50g")
    )

    // Création de la recette de croissant
    val croissantRecipe = Recipe(
        id = 1,
        name = "Croissant",
        steps = "Mélangez farine, sucre, sel. Incorporez beurre, levure délayée. Pétrissez, laissez reposer. Étalez, pliez en trois, répétez. Roulez en croissants, laissez lever. Dorez au jaune d'œuf, cuisez à 200°C.",
        imageResId = R.drawable.croissant,
        ingredients = ingredients
    )
fun main() {

    // Affichage de la recette
    println("Recette: ${croissantRecipe.name}")
    croissantRecipe.ingredients.forEach {
        println("${it.name}: ${it.quantity}")
    }
}
