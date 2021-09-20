
package com.mahima.animestreamingapp.ui.shop

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.classes.CartNotification
import com.mahima.animestreamingapp.entity.PetProductEntity
import com.mahima.animestreamingapp.entity.Product
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.repository.FavoritesRepository
import com.mahima.animestreamingapp.repository.ShoppingCartRepository
import com.mahima.animestreamingapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.time.LocalDateTime

class ProductDetailActivity : AppCompatActivity() {
//    decalring variables
    private lateinit var imgviewproductimg : ImageView
    private lateinit var tvproductname:TextView
    private lateinit var tvproductprice: TextView
    private lateinit var tvproductdescription : TextView
    private lateinit var btnaddtocart:Button
    private lateinit var etquantity:TextView
    private lateinit var btnaddtofavs : Button
    private lateinit var btnminus : Button
    private lateinit var btnplus : Button
    private var quantity : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

//        initializing variables
        imgviewproductimg = findViewById(R.id.imgviewproductimg)
        tvproductname = findViewById(R.id.tvproduct_name)
        tvproductdescription = findViewById(R.id.tvproductdescription)
        tvproductprice = findViewById(R.id.tvproduct_price)
        btnaddtocart=findViewById(R.id.btnaddtocart)
        etquantity=findViewById(R.id.etquantity)
        btnaddtofavs = findViewById(R.id.btnaddtofavs)
        btnminus = findViewById(R.id.btnminus)
        btnplus = findViewById(R.id.btnplus)


//        get intent
        val getintent = intent.getParcelableExtra<PetProductEntity>("productDetail")
        if(getintent != null){
            Glide.with(this)
                .load("http://192.168.1.80:80/"+getintent.productImage)
                .into(imgviewproductimg)
            tvproductname.setText(getintent.productName)
            tvproductprice.setText("Rs "+ "" + getintent.productPrice.toString())
            tvproductdescription.setText(getintent.productDescription)
        }

        btnplus.setOnClickListener {
            quantity = quantity + 1
            etquantity.text=quantity.toString()
        }
        btnminus.setOnClickListener {
            quantity=quantity-1
            etquantity.text=quantity.toString()
        }
//        click on add to cart
        btnaddtocart.setOnClickListener {
            //        get the quantity

//            check if quantity is empty
            if(etquantity.equals("")){
                etquantity.error = "Type a quantity"
                etquantity.requestFocus()
            }
            val shoppingCart = ShoppingCartEntity(
                userId = ServiceBuilder.userId,
                product = listOf(Product(
                    productId = getintent!!._id,
                    quantity= quantity.toInt()
                ))
            )
//            coroutines
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val repository = ShoppingCartRepository()
                    val response =repository.addProducts(
                        userId = ServiceBuilder.userId!!,
                        productId = getintent._id,
                        quantity = quantity

                    )
                    if(response.success == true){
                        withContext(Dispatchers.Main){
                            showNotification()
//                            Toast.makeText(
//                                this@ProductDetailActivity,
//                                "Added to cart",
//                                Toast.LENGTH_SHORT).show()
                        }
                        print(shoppingCart)
                    }

                }catch (ex: Exception){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProductDetailActivity, ex.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
//            Toast.makeText(this,ServiceBuilder.userId.toString(),Toast.LENGTH_SHORT).show()
        }

//        click to add to favorites
        btnaddtofavs.setOnClickListener { AddtoFavorites(getintent!!._id) }
    }

//    favorites funciton
    private fun AddtoFavorites(productId:String){

//        coroutines
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository= FavoritesRepository()
                val response = repository.addToFavorites(ServiceBuilder.userId!!,productId)

                if(response.success == true){
                    showFavsNotification()
//                    withContext(Dispatchers.Main){
//                        Toast.makeText(this@ProductDetailActivity,"Added to Favorites",Toast.LENGTH_SHORT).show()
//                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ProductDetailActivity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    notifications
    private  fun showNotification(){
        val notificationManager = NotificationManagerCompat.from(this)
        val  notificationChannels = CartNotification(this)
        notificationChannels.createNofiticationChannels()
        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_3)
        .setSmallIcon(R.drawable.trolley)
        .setContentTitle("Successful")
        .setContentText("Added to cart successfully")
        .setColor(Color.GREEN)
        .build()

        notificationManager.notify(3,notification)
    }

//    for favorites
    private  fun showFavsNotification(){
        val notificationManager = NotificationManagerCompat.from(this)
        val  notificationChannels = CartNotification(this)
        notificationChannels.createNofiticationChannels()
        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_4)
        .setSmallIcon(R.drawable.like)
        .setContentTitle("Successful")
        .setContentText("Added to favorites successfully")
        .setColor(Color.GREEN)
        .build()

        notificationManager.notify(4,notification)
    }
}