package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.database.UserDatabase
import com.mahima.animestreamingapp.entity.adminEntity
import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
//    declaring variables
    private lateinit var tvsignup:TextView
    private lateinit var btnlogin:Button
    private lateinit var etusername:EditText
    private lateinit var etpassword:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//initializing variables
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
// api way
    private fun userValidation() {
        val email = etusername.text.toString()
        val password = etpassword.text.toString()

//        inserting in user model
        val user = UserModel(email = email,password = password)
     CoroutineScope(Dispatchers.IO).launch {
        try{
            val repository = UserRepository()
            val response =repository.userLogin(email, password)
            if(response.success == true){
                ServiceBuilder.token = "Bearer ${response.token}"
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        this@LoginActivity,
                        "Successfully logged in",
                        Toast.LENGTH_SHORT).
                    show()
                    withContext(Dispatchers.Main){
                        startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
                    }
                }
            }else {
                withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity,"Invalid", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }catch (ex: Exception){
            withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity,ex.toString(), Toast.LENGTH_SHORT)
                    .show()
                }
            }

    }
    }
//    check user validation room way
//    private fun userValidation(){
////        get data
//        val username = etusername.text.toString()
//        val password = etpassword.text.toString()
//
//        var user : adminEntity ? =null
//        CoroutineScope(Dispatchers.IO).launch {
//            user=UserDatabase.getDatabase(this@LoginActivity).userDao().checkUser(username,password)
//            if(user == null){
//                withContext(Main){
//                    Toast.makeText(this@LoginActivity,"Invalid login credentials",Toast.LENGTH_SHORT).show()
//                }
//            }
//            else{
//                startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
////                if user exists save their user data
//                saveUserData()
//            }
//        }
//    }
//    for shared preferences
//    private fun saveUserData(){
////    creating a shared preferences file with mode private
//        val sharedpreferences=getSharedPreferences("UserLoginData", MODE_PRIVATE)
////    creating an editor
//        val editor=sharedpreferences.edit()
//
////        add data
//        editor.putString("username",etusername.text.toString())
//        editor.putString("password",etpassword.text.toString())
//        editor.apply()
//    }
}

