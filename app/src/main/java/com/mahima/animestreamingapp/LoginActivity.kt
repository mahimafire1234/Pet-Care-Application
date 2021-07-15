package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var tvsignup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvsignup=findViewById(R.id.tvsignuplink)

        tvsignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
        }
    }
}