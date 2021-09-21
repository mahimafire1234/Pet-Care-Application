package com.mahima.animestreamingapp.ui.myprofile

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import com.mahima.animestreamingapp.LoginActivity
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.classes.CartNotification
import com.mahima.animestreamingapp.databinding.MyprofileFragmentBinding
import com.mahima.animestreamingapp.ui.AboutusFragment
import com.mahima.animestreamingapp.ui.myprofile.MyprofileViewModel
import com.mahima.animestreamingapp.ui.shop.CartActivity
import com.mahima.animestreamingapp.ui.shop.FavoritesActivity

class MyprofileFragment : Fragment(),SensorEventListener {

    private lateinit var myprofileViewModel: MyprofileViewModel
    private var _binding: MyprofileFragmentBinding ? = null
    private lateinit var aboutus: CardView
    private lateinit var myorders : CardView
    private lateinit var cart : CardView
    private lateinit var myprofileinfo :CardView
    private lateinit var logout : CardView
    private lateinit var favorites : CardView
    private lateinit var hires : CardView

//    sensors
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?= null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myprofileViewModel =
            ViewModelProvider(this).get(MyprofileViewModel::class.java)

        _binding = MyprofileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        cardview
        aboutus = root.findViewById(R.id.aboutus)
        aboutus.setOnClickListener {

//            Toast.makeText(root.context, "Clicked on about us", Toast.LENGTH_LONG).show()
//            opens fragment
            requireActivity().supportFragmentManager.beginTransaction().apply {

                replace(R.id.linearContainer,AboutusFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

//        myorders
        myorders = root.findViewById(R.id.myorders)
        myorders.setOnClickListener {
            startActivity(
                Intent(requireContext(),OrderDetailActivity::class.java)
            )
//            Toast.makeText(root.context,"Clicked on my orders",Toast.LENGTH_SHORT).show()
//            requireActivity().supportFragmentManager.beginTransaction().apply {
//                replace(R.id.linearContainer,MyOrdersFragment())
//                    .addToBackStack(null)
//                    .commit()
//
//            }

        }

//        cart
        cart = root.findViewById(R.id.cart)
        cart.setOnClickListener {
            showCartActivty()
        }

//        myprfoile
        myprofileinfo =root.findViewById(R.id.myprofileinfo)
        myprofileinfo.setOnClickListener { showMyProfileInfo() }

//        logout
        logout = root.findViewById(R.id.logout)
        logout.setOnClickListener {
            logout()
            showLogoutNotification()
//            Toast.makeText(requireContext(),"Logged out",Toast.LENGTH_SHORT).show()

        }
//        for favorites
        favorites = root.findViewById(R.id.favorites)
        favorites.setOnClickListener {
            showFavsActivty()
//            Toast.makeText(requireContext(),"Clicked on favorites",Toast.LENGTH_SHORT).show()
        }
//for hires
        hires = root.findViewById(R.id.hires)
        hires.setOnClickListener {
            hires()
        }

//        /        for sensor
        //        initializing variables
        sensorManager = requireContext().getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager

        if(!checkSensor()){
            return null
        }else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    show cart
    private fun showCartActivty(){
        startActivity(
            Intent(requireContext(), CartActivity::class.java)
        )
    }
//    private open another fragment
    private fun showMyProfileInfo(){
        requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.linearContainer,UserInfoFragment())
            .addToBackStack(null)
            .commit()
    }
    }

    //    show cart btn
    private fun showFavsActivty(){
        startActivity(
            Intent(requireContext(), FavoritesActivity::class.java)
        )
    }

//    hires
    private fun hires(){
        startActivity(
            Intent(requireContext(),HireActivity::class.java)
        )
    }

//    logout
    private fun logout(){
        val sharedpreferences=requireActivity().getSharedPreferences("NewLogin", AppCompatActivity.MODE_PRIVATE)
        val editor=sharedpreferences.edit()
        editor.clear()
        editor.commit()
        startActivity(Intent(requireContext(),LoginActivity::class.java))
        finishAffinity(requireActivity())
    }

//    /    for sensor
    private fun checkSensor():Boolean{
        var sensorPresent =true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
            sensorPresent =false
        }
        return sensorPresent
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values
        val xAxis = values[0]
        val yAxis = values[1]
        val zAxis = values[2]

        if (xAxis>=5 && yAxis<0 && zAxis<=4 ){
            logout()
            Toast.makeText(requireContext(),"Logged out",Toast.LENGTH_SHORT).show()
            sensorManager.unregisterListener(this)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

//    show logout notification
    private  fun showLogoutNotification(){
        val notificationManager = NotificationManagerCompat.from(requireContext())
        val  notificationChannels = CartNotification(requireContext())
        notificationChannels.createNofiticationChannels()
        val notification = NotificationCompat.Builder(requireContext(), notificationChannels.CHANNEL_6)
        .setSmallIcon(R.drawable.like)
        .setContentTitle("Successful")
        .setContentText("Logged out  successfully")
        .setColor(Color.BLUE)
        .build()

        notificationManager.notify(6,notification)
}


}



