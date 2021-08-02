package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var tvsignup:TextView
    private lateinit var btnlogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvsignup=findViewById(R.id.tvsignuplink)
        btnlogin=findViewById(R.id.btnlogin)

        tvsignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
        }

        btnlogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
        }
    }
}
