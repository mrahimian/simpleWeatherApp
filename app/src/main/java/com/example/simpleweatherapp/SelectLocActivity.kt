package com.example.simpleweatherapp

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleweatherapp.retrofit.CityWeatherConditionInfo
import com.example.simpleweatherapp.retrofit.Rain
import com.example.simpleweatherapp.viewModel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

const val sharedPreferences = "sharedPrefs"
const val firstRun = "FIRST_ENTRY"

class SelectLocActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var getLocTextView: TextView
    lateinit var cityEntry: EditText
    lateinit var submitButton: Button
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_select_loc)
        firstEntry()

        builder = AlertDialog.Builder(this@SelectLocActivity)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocTextView = findViewById(R.id.get_current_loc)
        getLocTextView.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this@SelectLocActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getLoc()
            } else {
                ActivityCompat.requestPermissions(
                    this@SelectLocActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    44
                )
            }
        }
        cityEntry = findViewById(R.id.city_entry)
        submitButton = findViewById(R.id.submit_city)
        submitButton.setOnClickListener {
            getData(false,"","",cityEntry.text.toString())
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 44) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLoc()
            }
        }
    }

    private fun firstEntry() {
        //  MODE_PRIVATE means no other app can access this
        val sharedPreferences = getSharedPreferences(sharedPreferences, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(firstRun, true)
        editor.apply()
    }

    private fun getLoc() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                getData(true, location.latitude.toString(), location.longitude.toString(),"")
            }
        }
    }
    private fun getData(byLoc : Boolean, lat : String , lon : String, city : String) {
        var data: CityWeatherConditionInfo
        CoroutineScope(Dispatchers.Main).launch {
            data = if (!byLoc) {
                viewModel.sendCityAsParam(city)
            }else{
                viewModel.getByLoc(lat, lon)
            }
            if(data.rain == null){
                data.rain = Rain(0.0)
            }
            val intent = Intent(this@SelectLocActivity , MainActivity::class.java)
            intent.putExtra("data",data)
//            builder.setMessage(data.toString())
//            builder.create()
//            builder.show()
            startActivity(intent)
        }
    }

}