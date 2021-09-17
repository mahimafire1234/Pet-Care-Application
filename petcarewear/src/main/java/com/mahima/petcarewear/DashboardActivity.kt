package com.mahima.petcarewear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.petcarewear.model.PetProductModel
import com.mahima.petcarewear.repository.Petrepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {
//    get recyclerview
    private lateinit var recyclerviewwearos : RecyclerView
    companion object{
        private val petlistforrecyclerview: ArrayList<PetProductModel> = ArrayList<PetProductModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        getProducts()
//        recycler view
        recyclerviewwearos = findViewById(R.id.recyclerviewwearos)
        val adapter = recyclerviewadapter(this, petlistforrecyclerview)
        recyclerviewwearos.adapter = adapter
        recyclerviewwearos.layoutManager=LinearLayoutManager(this)
        petlistforrecyclerview.clear()

    }

//    get the data from api
    private fun getProducts(){
//        coroutines
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respository = Petrepository()
                val response = respository.showProducts()
                val data = response.data

//                insert in recyclerview
                for(item in data!!){
                    petlistforrecyclerview.add(
                        PetProductModel(
                            _id = item._id,
                            productName = item.productName,
                            productDescription = item.productDescription,
                            productPrice = item.productPrice,
                            productCategory = item.productCategory,
                            productImage = item.productImage
                        )
                    )
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@DashboardActivity,ex.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}