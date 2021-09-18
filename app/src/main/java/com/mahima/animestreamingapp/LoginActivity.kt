package com.mahima.animestreamingapp

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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

class LoginActivity : AppCompatActivity(),SensorEventListener {
//    declaring variables
    private lateinit var tvsignup:TextView
    private lateinit var btnlogin:Button
    private lateinit var etusername:EditText
    private lateinit var etpassword:EditText
//    for sensor
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//initializing variables
        tvsignup=findViewById(R.id.tvsignuplink)
        btnlogin=findViewById(R.id.btnlogin)
        etusername=findViewById(R.id.etusername)
        etpassword=findViewById(R.id.etpassword)

//        for sensor
        //        initializing variables
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if(!checkSensor()){
            return
        }else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

        tvsignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
        }

        btnlogin.setOnClickListener {
            if(etusername.text.toString().trim().isEmpty()){
                etusername.error="Fill this field"
                etusername.requestFocus()
                return@setOnClickListener
            }
            if(etpassword.text.toString().trim().isEmpty()){
                etpassword.error="Fill this field"
                etpassword.requestFocus()
                return@setOnClickListener
            }
            if(etusername.text.toString().trim().isEmpty() && etpassword.text.toString().trim().isEmpty()){
                Toast.makeText(this,"Please fill the inputs",Toast.LENGTH_LONG).show()
            }else{
                userValidation()
            }
        }
    }
// api way
     fun userValidation() {
        val email = etusername.text.toString()
        val password = etpassword.text.toString()

//
//        inserting in user model
        val user = UserModel(email = email,password = password)
     CoroutineScope(Dispatchers.IO).launch {
        try{
            val repository = UserRepository()
            val response =repository.userLogin(email, password)
            if(response.success == true){
                ServiceBuilder.token = "Bearer ${response.token}"
                ServiceBuilder.userId = response.userId
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        this@LoginActivity,
                        "Successfully logged in",
                        Toast.LENGTH_SHORT).
                    show()
                    withContext(Dispatchers.Main){
                        startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
                    }
//                    saveUserData(ServiceBuilder.token!!,ServiceBuilder.userId!!)
                }
            }else {
                withContext(Dispatchers.Main) {
                    if(response.message == "Email does not exist"){
                        Toast.makeText(this@LoginActivity,"Invalid email", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if(response.message=="Password does not match"){
                        Toast.makeText(this@LoginActivity,"Incorrect password", Toast.LENGTH_SHORT)
                            .show()
                    }

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

//    for shared preferences
    private fun saveUserData(token:String,userId:String){
//    creating a shared preferences file with mode private
        val sharedpreferences=getSharedPreferences("UserLoginData", MODE_PRIVATE)
//    creating an editor
        val editor=sharedpreferences.edit()

//        add data
        editor.putString("username",etusername.text.toString())
        editor.putString("password",etpassword.text.toString())
        editor.putString("token", token)
        editor.putString("userId", userId)
        editor.apply()
    }

//    for sensor
    private fun checkSensor():Boolean{
    var sensorPresent =true
    if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null){
        sensorPresent =false
    }
    return sensorPresent
}

    override fun onSensorChanged(event: SensorEvent?) {
        var valuex= event!!.values[0]
        val loginActivity = LoginActivity()
//        if (valuex<=5){
//            userValidation()
//            valuex=7.0F
//            Toast.makeText(this,"Logged in using sensor",Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}

