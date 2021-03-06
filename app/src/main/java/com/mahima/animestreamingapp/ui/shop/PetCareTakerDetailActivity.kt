package com.mahima.animestreamingapp.ui.shop

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.LoginActivity
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.PetCareTakerEntity
import com.mahima.animestreamingapp.repository.HireRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetCareTakerDetailActivity : AppCompatActivity(), SensorEventListener {
//    declaring variables
    private lateinit var tvfull_name: TextView
    private lateinit var tvage: TextView
    private lateinit var tvphonenum: TextView
    private lateinit var tvhireprice: TextView
    private lateinit var tvBio: TextView
    private lateinit var btnaddtocart:Button
    private lateinit var btnaddtofavs : Button
    private lateinit var imgviewproductimg : ImageView

//    gyroscope
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_care_taker_detail)

//        initializing
        tvfull_name= findViewById(R.id.tvfull_name)
        tvage = findViewById(R.id.tvage)
        tvphonenum =findViewById(R.id.tvphonenum)
        tvhireprice = findViewById(R.id.tvhireprice)
        tvBio = findViewById(R.id.tvBio)
        btnaddtocart=findViewById(R.id.btnaddtocart)
        imgviewproductimg=findViewById(R.id.imgviewproductimg)

//        gyroscoope
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if(!checkSensor()){
            return
        }else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

//        intent
        val intent= intent.getParcelableExtra<PetCareTakerEntity>("PetCareDetail")
        if(intent != null){

            tvfull_name.setText(intent.fullName)
            tvage.setText("Age:"+" " + intent.age.toString())
            tvphonenum.setText("Phone number:" +" "+ intent.phoneNum.toString())
            tvBio.setText(intent.Bio)
            tvhireprice.setText("Rs: " + intent.price.toString() +" "+ "per week")

            Glide.with(this)
                .load("http://192.168.1.80:80/"+intent.photo)
                .into(imgviewproductimg)

        }

//        hire
        btnaddtocart.setOnClickListener { Hire(intent!!._id) }
    }

//    hire
    private fun Hire(ProductId:String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = HireRepository()
                val response = repository.hire(ServiceBuilder.userId!!,ProductId)
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@PetCareTakerDetailActivity,"Hired!!",Toast.LENGTH_LONG).show()
                    }
                }
                if(response.success == false && response.message =="already hired"){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@PetCareTakerDetailActivity,"Hired already",Toast.LENGTH_LONG).show()
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@PetCareTakerDetailActivity,ex.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }

//    gyroscope
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
//        if (values>0 && values<1){
//            val secondintent= intent.getParcelableExtra<PetCareTakerEntity>("PetCareDetail")
//            Hire(secondintent!!._id)
//            sensorManager.unregisterListener(this)
//            finish()
//        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}