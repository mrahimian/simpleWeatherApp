package com.example.simpleweatherapp.retrofit

import androidx.lifecycle.LiveData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface WeatherService {

    @GET("data/2.5/weather?")
    suspend fun getCityWeatherInfo(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): CityWeatherConditionInfo

    @GET("data/2.5/weather?")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") app_id: String
    ): CityWeatherConditionInfo


}