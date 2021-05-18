package com.example.simpleweatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpleweatherapp.repository.GetImageRepo
import com.example.simpleweatherapp.repository.WeatherRepository
import com.example.simpleweatherapp.retrofit.CityWeatherConditionInfo


const val API_KEY = "470d81d24eb0bdd63681fc81fcbfdce0"

class MainViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository()
    private val imageRepository = GetImageRepo()

    var info = MutableLiveData<CityWeatherConditionInfo>()
    val inf1 = liveData {
        val t =weatherRepository.getCityWeatherInfo("tehran", API_KEY)
        emit(t)
    }

    suspend fun sendCityAsParam(city: String) = weatherRepository.getCityWeatherInfo(city, API_KEY)
    suspend fun getByLoc(lat: String, lon: String) = weatherRepository.getCurrentWeatherData(lat,lon, API_KEY)
    suspend fun getWeatherIcon( id : String )  = imageRepository.getWeatherIcon(id)
    /*{
        info = liveData {
            val inf = weatherRepository.getCityWeatherInfo(city, API_KEY)
            emit(inf)
        } as MutableLiveData<CityWeatherConditionInfo>

    }*/


    fun getByLoc2(lat: String, lon: String) {

    }

    /*fun getByLoc(lat: String, lon: String) =
        liveData {
            info = weatherRepository.getCurrentWeatherData(lat, lon, API_KEY)
            emit(info)
        }*/



}