package com.example.bagueton_v1.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagueton_v1.ui.model.WeatherAPI
import com.example.bagueton_v1.ui.model.WeatherBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel : ViewModel() {

    var weatherData = mutableStateListOf<WeatherBean>()

    fun readWeather() {
        println("Début de readWeather()")
        weatherData.clear()
        println("weatherData après clear: $weatherData")
        viewModelScope.launch(Dispatchers.Default) {
            try {
                println("Début de la coroutine")
                val weather = WeatherAPI.readWeather()
                println("Fin de la coroutine")
                if (weather != null) {
                    println("Weather data récupérée: $weather")
                    launch(Dispatchers.Main) {
                        weatherData.add(weather)
                        println("weatherData après ajout: $weatherData")
                    }
                } else {
                    println("Aucune donnée météo récupérée")
                }
                println("Fin de readWeather(), weather: $weather")
            } catch (e: IOException) {
                println("Erreur IO: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}