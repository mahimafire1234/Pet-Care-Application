package com.mahima.animestreamingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R

class AboutusFragment : Fragment() {
//    declaration of variables
    private lateinit var paragraphtext :TextView
    private lateinit var imgview:ImageView
    private lateinit var servicetext:TextView
    private lateinit var locationtv:TextView
    private lateinit var btnopenmaps:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        textview variable
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_aboutus, container, false)
//        getting the variable
        paragraphtext = view.findViewById(R.id.paragraphtext)
        servicetext=view.findViewById(R.id.servicetext)
        locationtv=view.findViewById(R.id.locationtv)
        btnopenmaps =view.findViewById(R.id.btnopenmaps)
//        setting the text
        paragraphtext.setText(
                "Jojo is a pet care app for owners that love their pets but have no time for them." +
                "Jojo helps in organizing care by helping owners shop for their pets and care for their pets."+
                "We love pets and your pets are our priority."

        )
        servicetext.setText("" +
                "Shop"+
                " Pet Care"
        )
        locationtv.setText("Chabahil, Kathmandu" + "" + " Softwarica sCollege")
//        adding image to glide
//            Glide.with(this)
//                .load("https://unsplash.com/photos/Hd7vwFzZpH0")
//                .into(imgview)

//        btnclick
        btnopenmaps.setOnClickListener {
            Toast.makeText(view.context,"clicked button",Toast.LENGTH_SHORT).show()
        }

        return view
    }
}