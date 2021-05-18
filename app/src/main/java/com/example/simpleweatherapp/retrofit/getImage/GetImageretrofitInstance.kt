package com.example.simpleweatherapp.retrofit.getImage

import com.example.simpleweatherapp.retrofit.WeatherService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetImageretrofitInstance {
    val imageService by lazy{
        val logging = HttpLoggingInterceptor()
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        Retrofit.Builder().
        baseUrl("http://openweathermap.org").
//        addConverterFactory(GsonConverterFactory.create()).
//        client(httpClient.build()).
        build().create(GetImageService::class.java)

    }

}