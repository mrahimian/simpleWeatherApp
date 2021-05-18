package com.example.simpleweatherapp.repository

import androidx.lifecycle.LiveData
import com.example.simpleweatherapp.retrofit.CityWeatherConditionInfo
import com.example.simpleweatherapp.retrofit.RetrofitInstance

class WeatherRepository {
    var weatherService = RetrofitInstance.weatherService

    suspend fun getCityWeatherInfo(q: String, appid: String) : CityWeatherConditionInfo =
        weatherService.getCityWeatherInfo(q, appid)

    suspend fun getCurrentWeatherData(
        lat: String,
        lon: String,
        appid: String
    ) = weatherService.getCurrentWeatherData(lat, lon, appid)

}