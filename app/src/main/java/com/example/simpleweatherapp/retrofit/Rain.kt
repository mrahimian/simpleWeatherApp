package com.example.simpleweatherapp.retrofit


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    @SerializedName("1h")
    val h: Double
) : Parcelable