package com.mahima.petcarewear

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mahima.petcarewear.api.ServiceBuilder
import com.mahima.petcarewear.databinding.ActivityLoginBinding
import com.mahima.petcarewear.model.UserModel
import com.mahima.petcarewear.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : Activity() {
    private lateinit var etemail : EditText
    private lateinit var etpassword : EditText
    private lateinit var btnlogin : Button

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initializing variables
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.btnlogin)

//        setonclicklistener
        btnlogin.setOnClickListener {
            userValidation()
        }

    }
    // api way
    private fun userValidation() {
        val email = etemail.text.toString()
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
//                    saveUserData()
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
}