package com.mahima.animestreamingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.entity.PetProductEntity

class adapterForPetProduct (
    var productList : ArrayList<PetProductEntity>
    ):RecyclerView.Adapter<adapterForPetProduct.ProductViewHolder>()
{
    class ProductViewHolder(view: View):RecyclerView.ViewHolder(view) {
//        vairables
        val tvproductname : TextView = view.findViewById(R.id.tvproductname)
        val tvproductprice : TextView =view.findViewById(R.id.tvproductPrice)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.productcard,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.tvproductname.text = product.productName
        holder.tvproductprice.text = "Rs" + " " + product.productPrice.toString()
    }

    override fun getItemCount(): Int {
        return productList.size!!
    }

}