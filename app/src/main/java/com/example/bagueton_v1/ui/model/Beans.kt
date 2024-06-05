package com.example.bagueton_v1.ui.model

//data class Ingredient(val name: String, val quantity: String)
//
//data class Recipe(
//    val id: Int,
//    val name: String,
//    val steps: String,
//    val imageResId: Int,
//    val ingredients: List<Ingredient>
//)



data class RecipeBean(
    var id: String? = null,
    var title: String,
    var images: List<Image>? = null,
    var ingredients: List<Ingredient>,
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

data class ContactFormBean(

    var  id: String?,
    var email: String,
    var message: String,
    var name: String

)
