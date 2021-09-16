package com.mahima.animestreamingapp.adapter

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
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.entity.PetCareTakerEntity
import com.mahima.animestreamingapp.ui.shop.PetCareTakerDetailActivity
import com.mahima.animestreamingapp.ui.shop.ProductDetailActivity

class adapterforPetCareTaker(
    private val context: Context,
    var petCareTakerList : ArrayList<PetCareTakerEntity>
):RecyclerView.Adapter<adapterforPetCareTaker.CareTakerHolder>()
{
    class CareTakerHolder(view: View):RecyclerView.ViewHolder(view) {
//    variables
        var tvfullname : TextView = view.findViewById(R.id.tvfullname)
        var tvhirerate : TextView = view.findViewById(R.id.tvhirerate)
        var imgproduct : ImageView = view.findViewById(R.id.imgproduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareTakerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.petcaretakercard,parent,false)
        return CareTakerHolder(view)
    }

    override fun onBindViewHolder(holder: CareTakerHolder, position: Int) {
        val careTaker = petCareTakerList[position]
        holder.tvfullname.text=careTaker.fullName
        holder.tvhirerate.text= "Rs : "+careTaker.price.toString()
        Glide.with(context)
            .load("http://192.168.1.80:80/"+careTaker.photo)
            .into(holder.imgproduct)


//        holder.imgproduct.setImageResource(R.drawable.food)

//        to open detail page
        //        view click listener
        holder.itemView.setOnClickListener {

//            send data to activity
            val intent = Intent(context, PetCareTakerDetailActivity::class.java)
            intent.putExtra("PetCareDetail",careTaker)
//            open activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return petCareTakerList.size!!
    }
}