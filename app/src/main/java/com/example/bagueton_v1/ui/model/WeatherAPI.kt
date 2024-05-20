package com.example.bagueton_v1.ui.model

import com.example.bagueton_v1.ui.WeatherViewModel
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun main(weatherViewModel: WeatherViewModel) {


}

// https://rapidapi.com/visual-crossing-corporation-visual-crossing-corporation-default/api/visual-crossing-weather/
object WeatherAPI {

    private val okHttpClient: OkHttpClient = OkHttpClient()
    private const val URL_WEATHER_API = "https://weather-api138.p.rapidapi.com/weather?city_name=Boisseron"
    private const val API_KEY = "7d820aac2amshf42f3c1c9950d96p1e627bjsnd5026d363db5"
    private const val API_HOST = "weather-api138.p.rapidapi.com"

    fun readWeather(): WeatherBean? {
        try {
            val json = sendGet(URL_WEATHER_API, API_KEY, API_HOST, okHttpClient)
            val res = Gson().fromJson(json, WeatherBean::class.java)
            println("/readweather: $res")
            return res
        } catch (e: JsonSyntaxException) {
            println("Erreur de syntaxe JSON: ${e.message}")
        } catch (e: IOException) {
            println("Erreur d'IO: ${e.message}")
        } catch (e: Exception) {
            println("Erreur inattendue: ${e.message}")
        }
        return null // Retourner null en cas d'erreur
    }
}



private fun sendGet(url: String, API_KEY : String, API_HOST : String, client: OkHttpClient): String {
    println("url : $url")
    val request = with(Request.Builder()) {
        url(url)
        get()
        addHeader("X-RapidAPI-Key", API_KEY)
        addHeader("X-RapidAPI-Host", API_HOST)
        build()
    }
    return client.newCall(request).execute().use { //it:Response
        if (!it.isSuccessful) {
            throw Exception("Réponse du serveur incorrect :${it.code}")
        }
        it.body.string()
    }
}
