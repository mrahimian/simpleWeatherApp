package com.example.simpleweatherapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.simpleweatherapp.retrofit.CityWeatherConditionInfo
import com.example.simpleweatherapp.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var cityName: TextView
    lateinit var  weatherState: TextView
    lateinit var  weatherDesc: TextView
    lateinit var degree: TextView
    lateinit var windSpeed: TextView
    lateinit var weatherIcon: ImageView

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setViews()

        val data = intent.getParcelableExtra<CityWeatherConditionInfo>("data") as CityWeatherConditionInfo
        cityName.text = data.name
        weatherState.text = data.weather[0].main
        weatherDesc.text = data.weather[0].description
        degree.text = (data.main.temp-273.15).toInt().toString() + "°"
        windSpeed.text = data.wind.speed.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png")
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread {
                weatherIcon.setImageBitmap(bmp)
            }
        }


    }

    fun setViews(){
        cityName = findViewById(R.id.city_name)
        weatherState = findViewById(R.id.weather_state)
        weatherDesc = findViewById(R.id.weather_desc)
        degree = findViewById(R.id.degree)
        windSpeed = findViewById(R.id.wind_speed)
        weatherIcon = findViewById(R.id.weather_icon)
    }
}