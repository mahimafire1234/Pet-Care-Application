package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SignupActivity : AppCompatActivity() {
//    declaration of variables
    private lateinit var usertypespinner:Spinner
    private lateinit var etusername:EditText
    private lateinit var etemail:EditText
    private lateinit var etpassword:EditText
    private lateinit var etconfirmpassword:EditText
    private lateinit var btnsignup:Button
    private lateinit var tvlogin:TextView

//    a list for user types
    private val userTypeList= arrayOf<String>("Admin","Pet owner")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//        initializing the above declared variables
        usertypespinner=findViewById(R.id.usertypespinner)
        etusername=findViewById(R.id.etusername)
        etemail=findViewById(R.id.etemail)
        etpassword=findViewById(R.id.etpassword)
        etconfirmpassword=findViewById(R.id.etconfirmpassword)
        btnsignup=findViewById(R.id.btnsignup)
        tvlogin=findViewById(R.id.tvlogin)

//        on click listener at log in
        tvlogin.setOnClickListener {
//            opening login activity
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        }

//        creating an adapter for spinner
        val spinner_adapter=ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            userTypeList
        )
        usertypespinner.adapter=spinner_adapter
//        code for selected spinner item left..


    }
}