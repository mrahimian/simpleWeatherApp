package com.example.simpleweatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.GoogleApiAvailability

class EnterActivity : AppCompatActivity() {
    private lateinit var cloud : ImageView
    private lateinit var appName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_enter)
        cloud =  findViewById(R.id.cloud)
        appName =  findViewById(R.id.app_name)
        enterAnimation()

        Log.e("Google service",GoogleApiAvailability().isGooglePlayServicesAvailable(this).toString())
    }

    private fun enterAnimation() {
        val x1: Float = cloud.translationX
        val y1: Float = cloud.translationX
        val x2: Float = appName.translationX
        val y2: Float = appName.translationX
        val tr = TranslateAnimation(x1, x1, y1 - 500, y1)
        val tr2 = TranslateAnimation(x2, x2, y2 + 400, y2)
        tr.duration = 3000
        tr2.duration = 3000
        tr.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
//                nextPage()
                startActivity(Intent(this@EnterActivity, SelectLocActivity::class.java))
                overridePendingTransition(R.anim.slide_left,R.anim.slide_right)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        cloud.startAnimation(tr)
        appName.startAnimation(tr2)
    }

    fun nextPage(){
        val sharedPreferences = getSharedPreferences(sharedPreferences , MODE_PRIVATE)
        if (!sharedPreferences.getBoolean(firstRun,false)) {
            startActivity(Intent(this@EnterActivity, SelectLocActivity::class.java))
            overridePendingTransition(R.anim.slide_left,R.anim.slide_right)
        }
        else{
            startActivity(Intent(this@EnterActivity, MainActivity::class.java))
            overridePendingTransition(R.anim.slide_left,R.anim.slide_right)
        }
        finish()
    }
}