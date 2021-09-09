package com.mahima.animestreamingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.entity.Product

class adapterForShoppingCart(
    private val context: Context,
    var productList : ArrayList<Product>
) : RecyclerView.Adapter<adapterForShoppingCart.ShoppingCartHolder>()
{
//    holder class
    class ShoppingCartHolder(view: View) : RecyclerView.ViewHolder(view){
//        layout variables declaration
        var imgproductcart : ImageView = view.findViewById(R.id.imgproductcart)
        var tvproductnamecart : TextView = view.findViewById(R.id.tvproductnamecart)
        var tvproductqtycart : TextView = view.findViewById(R.id.tvproductqtycart)
        var btndeleteforcart : ImageView = view.findViewById(R.id.btndeleteforcart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoppingcartlayout,parent,false)
        return ShoppingCartHolder(view)
    }

//    set the variables to the productlist values
    override fun onBindViewHolder(holder: ShoppingCartHolder, position: Int) {
        var product = productList[position]
//    setting the variables

//    image
        Glide.with(context)
        .load("http://192.168.1.80:80/"+product.productImage)
        .into(holder.imgproductcart)

//    product name
        holder.tvproductnamecart.setText(product.productName)
//    product qty
        holder.tvproductqtycart.setText("Quantity : "+" " + product.quantity)

    }

    override fun getItemCount(): Int {
        return productList.size!!
    }
}