package com.example.simpleweatherapp.repository

import com.example.simpleweatherapp.retrofit.getImage.GetImageretrofitInstance

class GetImageRepo {
    var ImageService = GetImageretrofitInstance.imageService

    suspend fun getWeatherIcon( id : String ) = ImageService.getWeatherIcon(id)

}