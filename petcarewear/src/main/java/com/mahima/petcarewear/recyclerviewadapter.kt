package com.mahima.petcarewear

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahima.petcarewear.model.PetProductModel

class recyclerviewadapter(
    private val context: Context,
    var productList : ArrayList<PetProductModel>
) :RecyclerView.Adapter<recyclerviewadapter.ProductViewHolder>()
{
    class ProductViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvproductname : TextView = view.findViewById(R.id.tvproductname)
        val tvproductprice : TextView =view.findViewById(R.id.tvproductPrice)
        val imgproduct : ImageView = view.findViewById(R.id.imgproduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardforproduct,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.tvproductname.text = product.productName
        holder.tvproductprice.text = "Rs" + " " + product.productPrice.toString()
        Glide.with(context)
            .load("http://192.168.1.80:80/"+product.productImage)
            .into(holder.imgproduct)

    }

    override fun getItemCount(): Int {
        return productList.size!!
    }
}