package com.c3dev.bagueton.ui.model

import com.c3dev.bagueton.ui.model.beans.ContactFormBean
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object ContactsFormAPI{

    private const val URL_SERVER = "http://90.51.140.217:30001/bagueton"
    // http://localhost:8080 http://90.51.140.217:8082/bagueton http://192.168.1.50:8082/bagueton


    fun createContactForm(name: String, email: String, message: String)  {

        val res: String = sendPost("$URL_SERVER/form", ContactFormBean(name, email, name, message))
             println("Réponse du serveur : $res")

    }

    private fun sendPost(url: String, toSend: Any?): String {
        println("url : $url")

        val json = RecipeAPI.gson.toJson(toSend)
        val body = json.toRequestBody(RecipeAPI.MEDIA_TYPE_JSON)
        val request = Request.Builder().url(url).post(body).build()

        return RecipeAPI.client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body?.string() ?: "manque des trucs"
        }
    }
}