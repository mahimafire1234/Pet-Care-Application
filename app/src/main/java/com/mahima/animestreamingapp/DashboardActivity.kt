package com.mahima.animestreamingapp

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mahima.animestreamingapp.databinding.ActivityDashboardBinding
import com.mahima.animestreamingapp.ui.shop.CartActivity
import com.mahima.animestreamingapp.ui.shop.FavoritesActivity

class DashboardActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sensorManager : SensorManager
    private var sensor : Sensor?= null
    private var success : Boolean = true
    private var permission : Boolean = true
    private var brightnessValue : Int =0

//    cart btn


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_shop, R.id.navigation_pet,R.id.navigation_profile,R.id.aboutus
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //        initializing sensormanager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

//        cart

//        if sensor does not exist
        if(!CheckSensor()){
            return
        }else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        }

    }
    //    function to check if light sensor is present or not
    private fun CheckSensor() : Boolean{
        var present =true

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null){
            present =false
        }
        return present
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]

//        condition to change the brightness
        if(values <15){
            AskPermission()
            setBrightness(240)
        }else if(values>80){
            AskPermission()
            setBrightness(50)
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    //    function to ask for permission
    private fun AskPermission(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            permission = Settings.System.canWrite(applicationContext)

            if(permission){
                success = true
            }
            else {
                var intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.setData(Uri.parse("package:"+applicationContext.packageName))
                startActivityForResult(intent,100)
            }

        }
    }


    //    set brightness
    private fun setBrightness( brightnessValue :Int){

        if(brightnessValue <0){
            this.brightnessValue =0
        }else if(brightnessValue>255){
            this.brightnessValue = 255
        }
        var contentResolver = applicationContext.contentResolver
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS,brightnessValue)


    }


}