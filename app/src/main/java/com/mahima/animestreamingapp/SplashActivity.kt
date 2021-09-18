package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.database.UserDatabase
import com.mahima.animestreamingapp.entity.adminEntity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private lateinit var logo: ImageView
    private lateinit var tvtext: TextView
    private lateinit var rel: RelativeLayout
//    username and password variable declaration for shared preferences
    var username : String ? =" "
    var password : String ? =" "
    var token : String ? =" "
    var userId : String ? =" "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        logo = findViewById(R.id.logo)
        tvtext = findViewById(R.id.tvtext)
        rel = findViewById(R.id.rel)
        //        for animation

        val animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        logo.setAnimation(animation)
        tvtext.setAnimation(animation1)

//        CoroutineScope(Dispatchers.Main).launch {
//            //suspend for the splash activity
//            delay(5000)
//            //open intent activity
//            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            //finish or destroy the splash screen
//            finish()
//        }

//        for shared preference
//        getusernamepassword()
        login()
    }

//    get username and password
    private fun getusernamepassword():Boolean{
        val sharedPreferences=getSharedPreferences("NewLogin", MODE_PRIVATE)
        username=sharedPreferences.getString("username"," ")
        password=sharedPreferences.getString("password", "")
        token =sharedPreferences.getString("token","")
        userId=sharedPreferences.getString("userId","")
        return username != ""  && password != ""
    }
////    login function
    private fun login(){
        CoroutineScope(Dispatchers.IO).launch {
//            delays the splash screen for 5 seconds
            delay(5000)
            if(getusernamepassword()){
                withContext(Dispatchers.Main){
                    startActivity(Intent(this@SplashActivity,DashboardActivity::class.java))
                    Toast.makeText(this@SplashActivity,token,Toast.LENGTH_SHORT).show()
                    ServiceBuilder.userId = userId
                    ServiceBuilder.token = token

                }
        }
        else{
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
        }
//            finishes the splash screen
            finish()
        }
    }
}


