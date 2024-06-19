package com.c3dev.bagueton.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c3dev.bagueton.ui.model.WeatherAPI
import com.c3dev.bagueton.ui.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class WeatherViewModel : ViewModel() {
var weatherData = mutableStateOf<WeatherResponse?>(null)


    fun weatherCallAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newWeatherData = WeatherAPI.fetchWeather()
                withContext(Dispatchers.Main) {
                    if (newWeatherData != null) {
                        println("Données de la réponse: $newWeatherData")
                        weatherData.value = newWeatherData
                    } else {
                        println("Réponse vide.")
                    }
                    println("Chargement des données météo dans le ViewModel")
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Afficher un message d'erreur sur le thread principal
                    println("Erreur de réseau: ${e.message}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Afficher un message d'erreur sur le thread principal
                    println("Erreur: ${e.message}")
                }
            }
        }
    }
}
