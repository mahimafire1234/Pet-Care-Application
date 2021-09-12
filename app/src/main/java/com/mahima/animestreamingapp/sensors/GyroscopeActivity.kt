package com.mahima.animestreamingapp.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mahima.animestreamingapp.LoginActivity
import com.mahima.animestreamingapp.R

class GyroscopeActivity : AppCompatActivity(),SensorEventListener {

//    declaring variables
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gyroscope)

//        initializing variables
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if(!checkSensor()){
            return
        }else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

//    function to check sensor
    private fun checkSensor():Boolean{
        var sensorPresent =true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null){
            sensorPresent =false
        }
        return sensorPresent
    }

    override fun onSensorChanged(event: SensorEvent?) {
       val values= event!!.values[1]
        val loginActivity = LoginActivity()
        if (values<0){
            loginActivity.userValidation()
            Toast.makeText(this,"Logged in using sensor",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}