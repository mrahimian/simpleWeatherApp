package com.example.simpleweatherapp.retrofit.getImage

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GetImageService {
    @Headers("Content-Type: image/png")
    @GET("img/wn/{id}@2x.png")
    suspend fun getWeatherIcon(
        @Path("id") id: String
    ): Call<ResponseBody>
}