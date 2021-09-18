package com.mahima.animestreamingapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.entity.PetEntity
import com.mahima.animestreamingapp.entity.PetProductEntity
import com.mahima.animestreamingapp.ui.home.AddPetFragment
import com.mahima.animestreamingapp.ui.home.HomeFragment
import com.mahima.animestreamingapp.ui.home.UpdatePetActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class adapterForRecyclerView(
    var PetList : ArrayList<PetEntity>,
    var context : Context
) :RecyclerView.Adapter<adapterForRecyclerView.PetViewHolder>(){

//    view holder class
    class  PetViewHolder(view: View):RecyclerView.ViewHolder(view){
    val petname: TextView = view.findViewById(R.id.tvpetname)
    val petage: TextView = view.findViewById(R.id.tvpetage)
    val pettype: TextView = view.findViewById(R.id.tvpettype)
    val petgender: TextView = view.findViewById(R.id.tvpetgender)
    val img : ImageView =view.findViewById(R.id.imgpet)
    val updatebtn : ImageView = view.findViewById(R.id.updatebtn)
    val deletebtn : ImageView = view.findViewById(R.id.deletebtn)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.petcard, parent, false)
        return PetViewHolder(view)    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = PetList[position]
        holder.petname.text = pet.petName
        holder.petage.text = pet.petAge
        holder.pettype.text = pet.petType
        holder.petgender.text = pet.petGender

        if (pet.petType.equals("Dog")) {
            holder.img.setImageResource(R.drawable.pet)
        } else {
            holder.img.setImageResource(R.drawable.cat)
        }

//        deletebtn
        holder.deletebtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete pet")
            builder.setMessage("Are you sure you want to delete your pet's information ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                deletePet(pet)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

//        update btn
        holder.updatebtn.setOnClickListener {
            val intent = Intent(context, UpdatePetActivity::class.java)
            intent.putExtra("pet",pet)
            context.startActivity(intent)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
       return PetList.size!!
    }

//    delete fun
    private fun deletePet(pet:PetEntity){
        CoroutineScope(Dispatchers.IO).launch {
            PetProductDatabase.getDatabase(context).petDao().deletePet(pet)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
            withContext(Dispatchers.Main){
                HomeFragment.PetList.remove(pet)
                notifyDataSetChanged()
            }
        }
    }

}
