package com.example.bagueton_v1.ui.model


import com.example.bagueton_v1.ui.model.RecipeAPI.MEDIA_TYPE_JSON
import com.example.bagueton_v1.ui.model.RecipeAPI.client
import com.example.bagueton_v1.ui.model.RecipeAPI.gson
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

object RecipeAPI {

    val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    val gson = Gson()
    val client = OkHttpClient()
    private const val URL_SERVER = "http://192.168.1.26:8082/bagueton"
    // http://localhost:8080 http://90.51.140.217:8082/bagueton http://192.168.1.50:8082/bagueton


    fun createRecipe(title: String, ingredients: List<Ingredient>, steps: List<Step>?){
        val res = sendPost("$URL_SERVER/createrecipe",
            RecipeBean(null, title, images = null, ingredients, steps )
        )
        println("Creation de la recette $title")
        println("Resultat: $res")
    }

    fun updateRecipe(idRecipe: String?, title: String?, ingredientList: List<Ingredient>?, stepList: List<Step>?) {
        val url = "$URL_SERVER/updaterecipe/$idRecipe"
        val recipeBean = title?.let {
            RecipeBean(null,
                it, images = null, ingredientList, stepList)
        }
        val res = sendPatch(url, recipeBean)
        println("Données de la recette '$title' modifiées")
        println(res)
    }

    // vérifier si le titre de la recette est déjà utilisé
    fun isTitleUsed(title: String): Boolean {
        val recipes = readRecipes()
        return recipes.any { it.title == title }
    }
    // récupérer toutes les recettes
    fun readRecipes() : List<RecipeBean> {
        try {
            val json = sendGet("$URL_SERVER/readrecipes")
            val res = Gson().fromJson(json, Array<RecipeBean>::class.java).toList()
            println("[[[ Lancement de la fonction readrecipes ]]]")
            return res
        } catch (e: JsonSyntaxException) {
            println("Erreur de syntaxe JSON: ${e.message}")
            // Gérer l'erreur de conversion JSON
        } catch (e: IOException) {
            println("Erreur d'IO: ${e.message}")
            // Gérer les erreurs d'entrée/sortie, par exemple un problème de réseau
        } catch (e: Exception) {
            println("Erreur inattendue: ${e.message}")
            // Gérer toute autre exception inattendue
        }
        return emptyList() // Retourner une liste vide en cas d'erreur
    }

    fun deleteRecipe(idRecipe: String?) {

        val res = sendDelete("$URL_SERVER/deleterecipe/$idRecipe")
        println(res)
        println("Recette <${idRecipe}> supprimée")

    }
}

private fun sendGet(url: String): String {
    println("url : $url")
    val request = Request.Builder().url(url).get().build()

    return client.newCall(request).execute().use { //it:Response
        if (!it.isSuccessful) {
            throw Exception("Réponse du serveur incorrect :${it.code}")
        }
        it.body.string()
    }
}

fun sendPost(url: String, toSend: Any?): String {
    println("url : $url")

    val json = gson.toJson(toSend)

    val body = json.toRequestBody(MEDIA_TYPE_JSON)
    val request = Request.Builder().url(url).post(body).build()

    return client.newCall(request).execute().use {
        if (!it.isSuccessful) {
            throw Exception("Réponse du serveur incorrect :${it.code}")
        }
        it.body.string()
    }
}

fun sendDelete(url: String): String {
    println("url : $url")
    val client = OkHttpClient()

    val request = Request.Builder().url(url).delete().build()

    return try {
        val response: Response = client.newCall(request).execute()
        response.body.string()
    } catch (e: IOException) {
        e.printStackTrace()
        "Error: ${e.message}"
    }
}

private fun sendPatch(url: String, toSend: Any?): String {
    println("url : $url")

    val json = gson.toJson(toSend)
    val body = json.toRequestBody(MEDIA_TYPE_JSON)
    val request = Request.Builder().url(url).patch(body).build()

    return client.newCall(request).execute().use {
        if (!it.isSuccessful) {
            throw Exception("Réponse du serveur incorrect :${it.code}")
        }
        it.body.string()
    }
}