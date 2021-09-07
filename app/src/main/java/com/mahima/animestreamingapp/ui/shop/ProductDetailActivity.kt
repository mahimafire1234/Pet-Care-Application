
package com.mahima.animestreamingapp.ui.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.PetProductEntity

class ProductDetailActivity : AppCompatActivity() {
//    decalring variables
    private lateinit var imgviewproductimg : ImageView
    private lateinit var tvproductname:TextView
    private lateinit var tvproductprice: TextView
    private lateinit var tvproductdescription : TextView
    private lateinit var btnaddtocart:Button
    private lateinit var etquantity:EditText

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

//        get the quantity
//        var quantity = etquantity.text.toString().toInt()

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
            Toast.makeText(this,ServiceBuilder.userId.toString(),Toast.LENGTH_SHORT).show()
        }


    }
}