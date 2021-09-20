package com.mahima.animestreamingapp.ui.shop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForShoppingCart
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.PetProductEntity
import com.mahima.animestreamingapp.entity.Product
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.repository.OrderRespository
import com.mahima.animestreamingapp.repository.ShoppingCartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartActivity : AppCompatActivity() {

    private var data: ShoppingCartEntity ?= null
    private var productList : List<Product> ?= null

//    checkout button
    private lateinit var btnforcheckout : Button
    companion object{
        var productListForRecyclerView:ArrayList<Product> = ArrayList<Product>()
    }
    private lateinit var tvbillcart : TextView
    private lateinit var tvnoitem : TextView

//    recycler view
    private lateinit var recyclerviewcart : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

//        initializing variables
        tvbillcart=findViewById(R.id.tvbillcart)
        btnforcheckout= findViewById(R.id.btnforcheckout)
        tvnoitem = findViewById(R.id.tvnoitem)

//        recyclerview
        recyclerviewcart = findViewById(R.id.recyclerviewcart)
        var adapter = adapterForShoppingCart(this,productListForRecyclerView!!)

        recyclerviewcart.layoutManager=LinearLayoutManager(this)
        recyclerviewcart.adapter = adapter
        productListForRecyclerView!!.clear()

//        btn click for checkout
        btnforcheckout.setOnClickListener { checkout() }
        

//coroutines to get the shopping cart
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val repository = ShoppingCartRepository()
                val response = repository.getProduct(ServiceBuilder.userId!!)
                if(response.success == true){
                    data = response.cart!!
                    productList= data!!.product

                    withContext(Dispatchers.Main){
//                        tvshowcart.setText(data!!.bill.toString())

                        if(data!=null){
                            //add to recyler view
                            for(i in productList!!){
                                productListForRecyclerView!!.add(
                                    Product(
                                        productId = i.productId,
                                        productName = i.productName,
                                        productImage = i.productImage,
                                        productPrice = i.productPrice,
                                        quantity = i.quantity
                                    )
                                )
                             }
                        }
                        else{
                            Toast.makeText(this@CartActivity,"No items to show",Toast.LENGTH_SHORT).show()
                        }

                        tvbillcart.setText("Bill:" + " "+ data!!.bill)
                    }
                }
            }
//            exception handling
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    tvnoitem.setText("No items to show")
                    tvbillcart.visibility=View.INVISIBLE
                    btnforcheckout.visibility=View.INVISIBLE
//                    Toast.makeText(this@CartActivity,ex.toString(),Toast.LENGTH_SHORT).show()
//                    tvshowcart.setText(ex.toString())
                }
            }
        }
    }

//    function for checkout
    private fun checkout(){
        startActivity(Intent(this,OrderActivity::class.java))
    }
}