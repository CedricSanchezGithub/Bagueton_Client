package com.c3dev.bagueton.ui.ui.screens.tests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.c3dev.bagueton.ui.WeatherViewModel

class WeatherActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherScreen(weatherViewModel)
        }

        // Appel de la fonction pour récupérer les données météo
        weatherViewModel.weatherCallAPI()
    }
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weatherData = viewModel.weatherData.value

    Surface() {
        Column(modifier = Modifier.padding(16.dp)) {
            if (weatherData != null) {
                Text(text = "Weather: ${weatherData.weather[0].main}")
                Text(text = "Temperature: ${weatherData.main.temp - 273.15} °C")
                Text(text = "Humidity: ${weatherData.main.humidity} %")
                Text(text = "Description: ${weatherData.weather[0].description}")
            } else {
                Text(text = "Loading weather data...")
            }
        }
    }
}
