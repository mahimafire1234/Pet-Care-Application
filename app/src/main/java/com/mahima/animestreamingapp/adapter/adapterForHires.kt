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
import com.mahima.animestreamingapp.entity.*
import com.mahima.animestreamingapp.repository.FavoritesRepository
import com.mahima.animestreamingapp.repository.ShoppingCartRepository
import com.mahima.animestreamingapp.ui.shop.CartActivity
import com.mahima.animestreamingapp.ui.shop.FavoritesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class adapterForHires(
    private val context: Context,
    var favsList: ArrayList<PetCareTakerHire>
): RecyclerView.Adapter<adapterForHires.MyHiresHolder>(){

    class MyHiresHolder(view: View):RecyclerView.ViewHolder(view){
        var fullNamehire : TextView = view.findViewById(R.id.fullNamehire)
        var age : TextView = view.findViewById(R.id.age)
        var hire_date : TextView =view.findViewById(R.id.hireDate)
        var imghire:ImageView= view.findViewById(R.id.imghire)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):MyHiresHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hirecard,parent,false)
        return MyHiresHolder(view)
    }

    override fun onBindViewHolder(holder: MyHiresHolder, position: Int) {

        var product = favsList[position]
//    setting the variables
//        for(i in product.petcaretaker!!){
            holder.fullNamehire.text="Full Name:"+" " + product.fullName
            Glide.with(context)
                .load("http://192.168.1.80:80/"+product.photo)
                .into(holder.imghire)
            holder.hire_date.text="Hire date:" +" "+ product.hire_date
        }

    override fun getItemCount(): Int {
        return favsList.size!!
    }
}