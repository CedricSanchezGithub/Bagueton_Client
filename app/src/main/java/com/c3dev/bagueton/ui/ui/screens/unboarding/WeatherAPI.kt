package com.c3dev.bagueton.ui.ui.screens.unboarding

import com.c3dev.bagueton.ui.model.beans.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit est utilisé sur l'API pour récupérer les données
// Interface pour définir les endpoints de l'API
interface WeatherApiService {
    @GET("weather")
    fun getWeatherData(
        @Query("q") town: String,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String
    ): Call<WeatherResponse>
}
// https://api.openweathermap.org/data/2.5/weather?q=Boisseron&appid=4b933d035bf6f0e8f16d78d9e5b41d8c&lang=FR

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
            println("response: $response")
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

