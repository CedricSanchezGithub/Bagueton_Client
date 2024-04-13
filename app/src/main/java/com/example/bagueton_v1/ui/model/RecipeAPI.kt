package com.example.bagueton_v1.ui.model


import com.example.bagueton_v1.ui.model.RecipeAPI.readRecipes
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

data class RecipeBean(
    val id_recipe: Long? = null,
    val title: String? = null,
    val image: String? = "R.drawable.addimg",
    val ingredients: String? = "liste d'ingrédients vide",
    val steps: String? = "liste d'étape vide"
)



object RecipeAPI {

    val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    val gson = Gson()
    val client = OkHttpClient()
    private const val URL_SERVER = "http://90.51.140.217:8081/bagueton"
    // http://localhost:8080 http://2.9.163.31:8081/bagueton


    fun createRecipe(title: String?, ingredients: String?, steps: String?){
        val res = sendPost("$URL_SERVER/createrecipe", RecipeBean(null, title, image = null, ingredients, steps))
        println("Données de la recette '$title' envoyées")
        println(res)
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


    fun readRecipes() : List<RecipeBean>{
        var json = sendGet("$URL_SERVER/readrecipes")
        val res = gson.fromJson(json, Array<RecipeBean>::class.java).toList()
        println("/readrecipes: $res")
        return res
    }

    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).get().build()

        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
}
