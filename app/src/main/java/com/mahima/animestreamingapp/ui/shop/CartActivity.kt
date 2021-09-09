package com.mahima.animestreamingapp.ui.shop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.mahima.animestreamingapp.repository.ShoppingCartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartActivity : AppCompatActivity() {

    private var data: ShoppingCartEntity ?= null
    private var productList : List<Product> ?= null
    companion object{
        var productListForRecyclerView:ArrayList<Product> = ArrayList<Product>()
    }
    private lateinit var tvbillcart : TextView

//    recycler view
    private lateinit var recyclerviewcart : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

//        declaring variables
        tvbillcart=findViewById(R.id.tvbillcart)

//        recyclerview
        recyclerviewcart = findViewById(R.id.recyclerviewcart)
        var adapter = adapterForShoppingCart(this,productListForRecyclerView!!)

        recyclerviewcart.layoutManager=LinearLayoutManager(this)
        recyclerviewcart.adapter = adapter
        productListForRecyclerView!!.clear()
        

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
                    Toast.makeText(this@CartActivity,ex.toString(),Toast.LENGTH_SHORT).show()
//                    tvshowcart.setText(ex.toString())
                }
            }
        }
    }
}