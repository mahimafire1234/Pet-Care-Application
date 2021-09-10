package com.mahima.animestreamingapp.ui.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.entity.OrderEntity

class OrderDetailActivity : AppCompatActivity() {
    private lateinit var tv :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)


    }
}