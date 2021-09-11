
package com.mahima.animestreamingapp.ui.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
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

class ProductDetailActivity : AppCompatActivity() {
//    decalring variables
    private lateinit var imgviewproductimg : ImageView
    private lateinit var tvproductname:TextView
    private lateinit var tvproductprice: TextView
    private lateinit var tvproductdescription : TextView
    private lateinit var btnaddtocart:Button
    private lateinit var etquantity:EditText
    private lateinit var btnaddtofavs : Button

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

//        click on add to cart
        btnaddtocart.setOnClickListener {
            //        get the quantity

            var quantity = etquantity.text.toString().toInt()
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
                            Toast.makeText(
                                this@ProductDetailActivity,
                                "Added to cart",
                                Toast.LENGTH_SHORT).show()
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
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ProductDetailActivity,"Added to Favorites",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ProductDetailActivity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}