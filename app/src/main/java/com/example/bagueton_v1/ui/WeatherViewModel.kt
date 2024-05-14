package com.example.bagueton_v1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagueton_v1.ui.model.WeatherAPI
import com.example.bagueton_v1.ui.model.WeatherBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.log

class WeatherViewModel : ViewModel() {

    private val weather = MutableLiveData<WeatherBean>()
    val weatherData: LiveData<WeatherBean> = weather


    fun readWeather() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                weather.value = WeatherAPI.readWeather()

            }  catch (e: IOException) {
                e.printStackTrace()
            }

        }

    }


}