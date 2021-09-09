package com.mahima.animestreamingapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.Product
import com.mahima.animestreamingapp.repository.ShoppingCartRepository
import com.mahima.animestreamingapp.ui.shop.CartActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

//    delete button
    holder.btndeleteforcart.setOnClickListener {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete item")
        builder.setMessage("Are you sure you want to delete this item from your cart ??")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { _, _ ->
            delete(product)
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    }

    override fun getItemCount(): Int {
        return productList.size!!
    }

//    delete function
    private fun delete(product: Product){
//        coroutines

        CoroutineScope(Dispatchers.IO).launch {

            val repository = ShoppingCartRepository()
            val response = repository.deleteItem(ServiceBuilder.userId!!,product.productId!!)

            try{
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show()
                        CartActivity.productListForRecyclerView.remove(product)
                        notifyDataSetChanged()
                    }
                }
            }

            catch(ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,ex.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}