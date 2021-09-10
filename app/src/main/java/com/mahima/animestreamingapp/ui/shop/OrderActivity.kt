package com.mahima.animestreamingapp.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.repository.OrderRespository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderActivity : AppCompatActivity() {

    private lateinit var etpayment : EditText
    private lateinit var etdelivery_address : EditText
    private lateinit var btnorder : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

//        initializing variables
        etpayment = findViewById(R.id.etpayment)
        etdelivery_address = findViewById(R.id.etdelivery_address)
        btnorder = findViewById(R.id.btnorder)

        btnorder.setOnClickListener { order() }
    }

    private fun order(){
        var payment = etpayment.text.toString()
        var delivery_address = etdelivery_address.text.toString()

//        coroutines

        CoroutineScope(Dispatchers.IO).launch {

            try {

                val repository = OrderRespository()
                val response = repository.createOrder(ServiceBuilder.userId!!,payment,delivery_address)
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@OrderActivity,"Checkout complete",Toast.LENGTH_SHORT).show()
                    }
                }

            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@OrderActivity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}