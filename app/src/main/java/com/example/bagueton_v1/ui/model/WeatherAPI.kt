package com.example.bagueton_v1.ui.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Définition des classes de données pour correspondre au JSON reçu
data class Coord(val lon: Double, val lat: Double)
data class Weather(val id: Int, val main: String, val description: String, val icon: String)
data class Main(val temp: Double, val feels_like: Double, val temp_min: Double, val temp_max: Double, val pressure: Int, val humidity: Int)
data class Wind(val speed: Double, val deg: Int)
data class Clouds(val all: Int)
data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Long, val sunset: Long)
data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

// Interface pour définir les endpoints de l'API
interface WeatherApiService {
    @GET("weather")
    fun getWeatherData(
        @Query("q") town: String,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String
    ): Call<WeatherResponse>
}

object WeatherAPI {
    private const val URL_API = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "4b933d035bf6f0e8f16d78d9e5b41d8c"
    private const val TOWN = "Boisseron"
    private const val LANG = "FR"
    fun fetchWeather(): WeatherResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherApiService::class.java)
        val call = service.getWeatherData(TOWN, API_KEY, LANG)

        return try {
            val response = call.execute()
            println(response)
            if (response.isSuccessful) {
                response.body()
            } else {
                println("Échec de la requête: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

fun main() {
    WeatherAPI.fetchWeather()
}

// private fun executeCall() {
//    launch(Dispatchers.Main) {
//        try {
//            val response = ApiClient.apiService.getPostById(1)
//
//            if (response.isSuccessful && response.body() != null) {
//                val content = response.body()
//                //do something
//            } else {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Error Occurred: ${response.message()}",
//                     Toast.LENGTH_LONG
//                ).show()
//            }
//
//        } catch (e: Exception) {
//            Toast.makeText(
//                this@MainActivity,
//                "Error Occurred: ${e.message}",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//}
