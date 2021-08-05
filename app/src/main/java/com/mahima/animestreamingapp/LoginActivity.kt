package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.mahima.animestreamingapp.database.UserDatabase
import com.mahima.animestreamingapp.entity.adminEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var tvsignup:TextView
    private lateinit var btnlogin:Button
    private lateinit var etusername:EditText
    private lateinit var etpassword:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvsignup=findViewById(R.id.tvsignuplink)
        btnlogin=findViewById(R.id.btnlogin)
        etusername=findViewById(R.id.etusername)
        etpassword=findViewById(R.id.etpassword)

        tvsignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
        }

        btnlogin.setOnClickListener {
            userValidation()
        }
    }
//    check user validation
    private fun userValidation(){
//        get data
        val username = etusername.text.toString()
        val password = etpassword.text.toString()

        var user : adminEntity ? =null
        CoroutineScope(Dispatchers.IO).launch {
            user=UserDatabase.getDatabase(this@LoginActivity).userDao().checkUser(username,password)
            if(user == null){
                withContext(Main){
                    Toast.makeText(this@LoginActivity,"Invalid login credentials",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
//                if user exists save their user data
                saveUserData()
            }
        }
    }
//    for shared preferences
    private fun saveUserData(){
        val sharedpreferences=getSharedPreferences("UserLoginData", MODE_PRIVATE)
        val editor=sharedpreferences.edit()

//        add data
        editor.putString("username",etusername.text.toString())
        editor.putString("password",etpassword.text.toString())
        editor.apply()
    }
}
