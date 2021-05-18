package com.example.simpleweatherapp.retrofit


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityWeatherConditionInfo(
    @SerializedName("base")
    val base: String = "",
    @SerializedName("clouds")
    val clouds: Clouds = Clouds(0),
    @SerializedName("cod")
    val cod: Int = 0,
    @SerializedName("coord")
    val coord: Coord = Coord(0.0,0.0),
    @SerializedName("dt")
    val dt: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: Main = Main(0.0,0,0,0.0,0.0,0.0),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("rain")
    var rain: Rain = Rain(0.0),
    @SerializedName("sys")
    val sys: Sys = Sys("",0,0,0,0),
    @SerializedName("timezone")
    val timezone: Int = 0,
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind = Wind(0,0.0)
) : Parcelable