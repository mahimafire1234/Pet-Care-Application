package com.mahima.animestreamingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.entity.OrderEntity
import com.mahima.animestreamingapp.entity.ProductOrder
import com.mahima.animestreamingapp.ui.myprofile.OrderDetailActivity

class adapterForMyOrders (
    private val context: Context,
    var orderList: ArrayList<OrderEntity>
    ):RecyclerView.Adapter<adapterForMyOrders.MyOrderHolder>()
{
        class MyOrderHolder(view: View):RecyclerView.ViewHolder(view){
            var tvproductnameorder : TextView = view.findViewById(R.id.tvproductnameorder)
            var tvproductqtyorder : TextView = view.findViewById(R.id.tvproductqtyorder)
            var tvdeliveryaddress : TextView = view.findViewById(R.id.tvdeliveryaddress)
            var tvorderdate : TextView = view.findViewById(R.id.tvorderdate)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ordercard,parent,false)
        return MyOrderHolder(view)
    }

    override fun onBindViewHolder(holder: MyOrderHolder, position: Int) {
        var order = orderList[position]
        holder.tvorderdate.text = "Order Date:"+ order.date_added
        holder.tvdeliveryaddress.text = " Delivery Address: "+order.delivery_address.toString()
        for(i in order.product!!){
            holder.tvproductnameorder.append("Product Name:" + i.productName +" "+  "Quantity :"+i.quantity+"\n" )

        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}