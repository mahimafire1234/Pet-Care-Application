package com.mahima.animestreamingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.entity.PetEntity
import com.mahima.animestreamingapp.entity.PetProductEntity

class adapterForRecyclerView(
    var PetList : ArrayList<PetEntity>
) :RecyclerView.Adapter<adapterForRecyclerView.PetViewHolder>(){

//    view holder class
    class  PetViewHolder(view: View):RecyclerView.ViewHolder(view){
    val petname: TextView = view.findViewById(R.id.tvpetname)
    val petage: TextView = view.findViewById(R.id.tvpetage)
    val pettype: TextView = view.findViewById(R.id.tvpettype)
    val petgender: TextView = view.findViewById(R.id.tvpetgender)
    val img : ImageView =view.findViewById(R.id.imgpet)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.petcard, parent, false)
        return PetViewHolder(view)    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet=PetList[position]
        holder.petname.text=pet.petName
        holder.petage.text=pet.petAge
        holder.pettype.text=pet.petType
        holder.petgender.text=pet.petGender

        if (pet.petType == "Dog"){
            holder.img.setImageResource(R.drawable.pet)
        }else{
            holder.img.setImageResource(R.drawable.cat)
        }
    }

    override fun getItemCount(): Int {
       return PetList.size!!
    }

}