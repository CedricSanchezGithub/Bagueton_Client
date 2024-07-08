package com.c3dev.bagueton.ui.model.beans

data class RecipeBean(
    var id: String? = null,
    var title: String,
    var images: List<Image>? = null,
    var ingredients: List<Ingredient>? = null,
    var steps: List<Step>? = null
)

data class Image(
    var id: String? = null,
    var url: String
)

data class Ingredient(
    var id: String ? = null,
    var ingredient: String?,
    var quantity: String?
)

data class Step(
    var description: String?,
    var id: String ? = null
)

