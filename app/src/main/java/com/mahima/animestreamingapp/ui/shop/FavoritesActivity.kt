package com.mahima.animestreamingapp.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForMyFavorites
import com.mahima.animestreamingapp.adapter.adapterForShoppingCart
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.FavoritesEntity
import com.mahima.animestreamingapp.entity.Product
import com.mahima.animestreamingapp.entity.ProductFavorites
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class FavoritesActivity : AppCompatActivity() {
    private lateinit var recyclerviewfavorites:RecyclerView
    private var data: FavoritesEntity ?= null
    private var productList : List<ProductFavorites> ?= null
    companion object{
        var productListForRecyclerView:ArrayList<ProductFavorites> = ArrayList<ProductFavorites>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

//        initializing variables
        recyclerviewfavorites = findViewById(R.id.recyclerviewfavorites)

//        recycler view
        var adapter = adapterForMyFavorites(this, productListForRecyclerView!!)

        showFavorites()
        recyclerviewfavorites.layoutManager= LinearLayoutManager(this)
        recyclerviewfavorites.adapter = adapter
        productListForRecyclerView!!.clear()

    }

    private fun showFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = FavoritesRepository()
                val response = repository.showFavorites(ServiceBuilder.userId!!)

                if(response.success == true){
                    data = response.favoriteItem!!
                    productList = data!!.product

                    withContext(Dispatchers.Main){
                        if(data!=null){
                            for(i in productList!!){
                                productListForRecyclerView.add(
                                    ProductFavorites(
                                        productId = i.productId,
                                        productName = i.productName,
                                        productImage = i.productImage

                                    )
                                )
                            }
                        }
                    }

                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@FavoritesActivity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}