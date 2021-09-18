package com.mahima.animestreamingapp.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForHires
import com.mahima.animestreamingapp.adapter.adapterForMyOrders
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.HireEntity
import com.mahima.animestreamingapp.entity.OrderEntity
import com.mahima.animestreamingapp.entity.PetCareTakerHire
import com.mahima.animestreamingapp.entity.ProductOrder
import com.mahima.animestreamingapp.repository.HireRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HireActivity : AppCompatActivity() {
    private lateinit var recyclerviewhire : RecyclerView
    private var data: HireEntity ?= null
//    companion object for recycler view
    companion object{
        var hireListForRecyclerView:ArrayList<PetCareTakerHire> = ArrayList<PetCareTakerHire>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hire)

        getHire()
        val adapter= adapterForHires(this, hireListForRecyclerView)
        recyclerviewhire=findViewById(R.id.recyclerviewhire)
        recyclerviewhire.layoutManager= LinearLayoutManager(this)
        recyclerviewhire.adapter = adapter
        hireListForRecyclerView.clear()
        adapter.notifyDataSetChanged()
    }

    private fun getHire(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = HireRepository()
                val response = repository.shoeHires(ServiceBuilder.userId!!)
                data = response.hiredITem
                if(response.success == true){

                    for(i in data!!.petcaretaker!!){
                        hireListForRecyclerView.add(
                            PetCareTakerHire(
                                petcaretakerId = i.petcaretakerId,
                                fullName = i.fullName,
                                photo = i.photo,
                                hire_date = i.hire_date,
                                price = i.price
                            )
                        )
                    }
//                    withContext(Dispatchers.Main){
//                        Toast.makeText(this@HireActivity,data.toString(),Toast.LENGTH_LONG).show()
//                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@HireActivity,ex.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}