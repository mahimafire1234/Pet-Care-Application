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
import com.mahima.animestreamingapp.entity.FavoritesEntity
import com.mahima.animestreamingapp.entity.OrderEntity
import com.mahima.animestreamingapp.entity.ProductFavorites
import com.mahima.animestreamingapp.repository.FavoritesRepository
import com.mahima.animestreamingapp.repository.ShoppingCartRepository
import com.mahima.animestreamingapp.ui.shop.CartActivity
import com.mahima.animestreamingapp.ui.shop.FavoritesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class adapterForMyFavorites(
    private val context: Context,
    var favsList: ArrayList<ProductFavorites>
): RecyclerView.Adapter<adapterForMyFavorites.MyFavoritesHolder>(){

    class MyFavoritesHolder(view: View):RecyclerView.ViewHolder(view){
        var tvproductnamefavs : TextView = view.findViewById(R.id.tvproductnamefavs)
        var imgproductfavs : ImageView = view.findViewById(R.id.imgproductfavs)
        var btndeleteforfavs : ImageView =view.findViewById(R.id.btndeleteforfavs)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoritesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favoritescard,parent,false)
        return adapterForMyFavorites.MyFavoritesHolder(view)
    }

    override fun onBindViewHolder(holder: MyFavoritesHolder, position: Int) {
        var product = favsList[position]
//    setting the variables

//    image
        Glide.with(context)
            .load("http://192.168.1.80:80/"+product.productImage)
            .into(holder.imgproductfavs)

//    product name
        holder.tvproductnamefavs.setText(product.productName)

//        delete item
        holder.btndeleteforfavs.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete item")
            builder.setMessage("Are you sure you want to delete this item from your favorites ??")
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
        return favsList.size
    }

//    delet function
    private fun delete(product:ProductFavorites){
    CoroutineScope(Dispatchers.IO).launch {

        val repository = FavoritesRepository()
        val response = repository.deleteFavorites(ServiceBuilder.userId!!,product.productId!!)

        try{
            if(response.success == true){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show()
                    FavoritesActivity.productListForRecyclerView.remove(product)
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