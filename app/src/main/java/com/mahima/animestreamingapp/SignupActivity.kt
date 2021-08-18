package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
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

//        click on sign up
        btnsignup.setOnClickListener {
            check()
            register()
            clear()
//            Toast.makeText(this@SignupActivity, "Registered successfully ", Toast.LENGTH_SHORT).show()
            loginRedirect()
        }
    }
//the api way
    private fun register() {
        val username = etusername.text.toString()
        val email = etemail.text.toString()
        val password = etpassword.text.toString()
        val confirmpassword =  etconfirmpassword.text.toString()

//        check if fields are empty
//        check if password matches
        if(confirmpassword != password ){
            etconfirmpassword.error = "Passwords don't match"
            etconfirmpassword.requestFocus()
        }else{
            val user = UserModel(
                fullName = username,
                email = email,
                password = password
            )
//            coroutines part
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val repository = UserRepository()
                    val response =repository.userRegister(user)
                    if(response.success == true){
                        withContext(Main){
                            Toast.makeText(this@SignupActivity,"Registered successfully",Toast.LENGTH_LONG).show()
                        }
                    }
                }catch (ex:Exception){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignupActivity, ex.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }
//    the room database way
//    function for registration and insertion to room

//    private fun register(){
//
////        get the data from ui
//        val username = etusername.text.toString()
//        val email = etemail.text.toString()
//        val password = etpassword.text.toString()
//        val confirmpassword =  etconfirmpassword.text.toString()
//
////        check if fields are empty
////        check if password matches
//        if(confirmpassword != password ){
//            etconfirmpassword.error = "Passwords don't match"
//            etconfirmpassword.requestFocus()
//        }
////        database registration
//        val insertData = adminEntity(username = username, email = email ,password = password)
//        CoroutineScope(Dispatchers.IO).launch {
//            UserDatabase.getDatabase(this@SignupActivity).userDao().register(insertData)
//        }
//        Toast.makeText(this, "User registered successfully",Toast.LENGTH_SHORT).show()
//
//    }
//    check data
    private fun check(){

        if(etusername.text.toString().isEmpty()){
            etusername.error = "Username field is empty"
            etusername.requestFocus()
        }
        if(etemail.text.toString().isEmpty()){
            etemail.error = "Email field is empty"
            etemail.requestFocus()
        }
        if(etpassword.text.toString().isEmpty()){
            etpassword.error = "Password field is empty"
            etpassword.requestFocus()
        }
        if(etconfirmpassword.text.toString().isEmpty()){
            etconfirmpassword.error = "Confirm Password is empty"
            etconfirmpassword.requestFocus()
        }
    }
//    clear
    private fun clear(){
        etusername.setText("")
        etemail.setText("")
        etpassword.setText("")
        etconfirmpassword.setText("")
    }
//    login direct
    private fun loginRedirect(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}