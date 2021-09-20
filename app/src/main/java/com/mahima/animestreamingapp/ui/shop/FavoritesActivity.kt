package com.mahima.animestreamingapp.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
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
    private lateinit  var noitem : TextView
    companion object{
        var productListForRecyclerView:ArrayList<ProductFavorites> = ArrayList<ProductFavorites>()
    }
    private lateinit var showitem : TextView

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

//        show item
        showitem = findViewById(R.id.showitem)
        noitem = findViewById(R.id.noitem)

    }

    private fun showFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = FavoritesRepository()
                val response = repository.showFavorites(ServiceBuilder.userId!!)

                if(response.success == true){
                    data = response.favoriteItem!!
                    productList = data!!.product
                    val length = productList!!.size

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
                        showitem.text="Showing items :" + length + " "+"of" + " "+length
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    noitem.text = "No items in favorites"
                    showitem.visibility=View.INVISIBLE
//                    Toast.makeText(this@FavoritesActivity,ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}