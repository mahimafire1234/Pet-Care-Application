package com.mahima.animestreamingapp.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    private lateinit var tvcheck:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

//        initializing variables
        etpayment = findViewById(R.id.etpayment)
        etdelivery_address = findViewById(R.id.etdelivery_address)
        btnorder = findViewById(R.id.btnorder)
        tvcheck = findViewById(R.id.tvcheck)

        btnorder.setOnClickListener {
//            check if fields are empty
            if(etpayment.text.toString().trim().isEmpty()){
                etpayment.error = "Fill in your card number"
                etpayment.requestFocus()
                return@setOnClickListener
            }
            if(etpayment.text.toString().length<12){
                etpayment.error = "Card number cannot be less than 12"
                etpayment.requestFocus()
                return@setOnClickListener
            }
            if(etdelivery_address.text.toString().trim().isEmpty()){
                etdelivery_address.error = "Delivery address cannot be empty"
                etdelivery_address.requestFocus()
                return@setOnClickListener
            }
            if(etdelivery_address.text.toString().trim().isEmpty() && etpayment.text.toString().trim().isEmpty()){
                etpayment.error="This field cannot be empty"
                etdelivery_address.error = "This field cannot be empty"
                etpayment.requestFocus()
            }
            else{
                order()
            }
        }
    }

    private fun order(){
        var payment = etpayment.text.toString()
        var delivery_address = etdelivery_address.text.toString()

//        coroutines

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = OrderRespository()
                val response = repository.createOrder(ServiceBuilder.userId!!,payment.toLong(),delivery_address)

                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@OrderActivity,"Checkout complete",Toast.LENGTH_SHORT).show()

                    }
                }

            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
//                    tvcheck.append(ex.toString())
                    Toast.makeText(this@OrderActivity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}