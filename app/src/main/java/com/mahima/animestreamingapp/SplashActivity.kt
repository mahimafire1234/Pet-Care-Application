package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo=findViewById(R.id.logo)
        tvtext=findViewById(R.id.tvtext)
        rel=findViewById(R.id.rel)
        //        for animation

        val animation=AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val animation1=AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        logo.setAnimation(animation)
        tvtext.setAnimation(animation1)

//        for shared preference
        getusernamepassword()
        login()
    }

//    get username and password
    private fun getusernamepassword(){
        val sharedPreferences=getSharedPreferences("UserLoginData", MODE_PRIVATE)
        username=sharedPreferences.getString("username"," ")
        password=sharedPreferences.getString("password", "")
    }
//    login function
    private fun login(){
        var user : adminEntity? =null
        CoroutineScope(Dispatchers.IO).launch {
//            delays the splash screen for 5 seconds
            delay(5000)
            user= UserDatabase.getDatabase(this@SplashActivity).userDao().checkUser(username!!,password!!)
            if(user == null){
                withContext(Dispatchers.Main){
                    startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            }
        }
        else{
            startActivity(Intent(this@SplashActivity,DashboardActivity::class.java))
        }
//            finishes the splash screen
            finish()
        }
    }
}

