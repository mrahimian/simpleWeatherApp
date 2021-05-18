package com.example.simpleweatherapp.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val weatherService by lazy{
        val logging = HttpLoggingInterceptor()
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        Retrofit.Builder().
                baseUrl("http://api.openweathermap.org").
                addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).
                client(httpClient.build()).
                build().create(WeatherService::class.java)
    }
}