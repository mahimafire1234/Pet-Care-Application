package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        using coroutine for splash activity
        CoroutineScope(Dispatchers.Main).launch{
//            suspend for the splash activity
            delay(2000)
//            open intent activity
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
//            finish or destroy the splash screen
            finish()


        }
    }
}