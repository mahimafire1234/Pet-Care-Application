package com.mahima.animestreamingapp.ui.shop

import android.app.Application
import android.app.Service
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForPetProduct
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.entity.PetEntity
import com.mahima.animestreamingapp.entity.PetProductEntity
import com.mahima.animestreamingapp.model.PetProductModel
import com.mahima.animestreamingapp.repository.PetProductRespository
import com.mahima.animestreamingapp.repository.PetRepository
import com.mahima.animestreamingapp.response.petresponse
import com.mahima.animestreamingapp.viewmodel.viewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Connection
import okhttp3.Response
import java.lang.Exception
import java.net.ConnectException

class PetItemFragment : Fragment() {
//    declaration of variables
    private lateinit var recyclerview:RecyclerView
    private var data= ArrayList<PetProductEntity>()
    companion object{
        private lateinit var repository: PetProductRespository
        private lateinit var response:petresponse
        val ProductList: ArrayList<PetProductEntity> = ArrayList<PetProductEntity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_pet_item, container, false)
        recyclerview=view.findViewById(R.id.recyclerview)

        //adapter for recyclerview
        val adapter = adapterForPetProduct(requireContext(),ProductList)
//        
        CoroutineScope(Dispatchers.IO).launch {
            try {
//                gets data from repository
                repository = PetProductRespository()
                response = repository.showProducts()
                data = response.data!!
//                inserts data into room database
                insertRb()
                withContext(Main){
                    recyclerview.layoutManager=GridLayoutManager(requireContext(),2)
                    recyclerview.adapter=adapter
                    ProductList.clear()
                    getProducts()
                }
                //        calling cuntion to show products
            }
            catch (ex:Exception){
                withContext(Main){
                Toast.makeText(requireContext(),ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }
//    function to insert data in room database
    private fun insertRb(){
        for(i in data){
            val insertData = PetProductEntity(
                _id=i._id,
                productName = i.productName!!,
                productDescription = i.productDescription!!,
                productPrice = i.productPrice!!,
                productCategory = i.productCategory!!,
                productImage = i.productImage!!
            )
            PetProductDatabase.getDatabase(requireContext()).petProductDao().insertProduct(insertData)
        }
    }
//    function to get products
    private fun getProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            val showData=PetProductDatabase.getDatabase(requireContext()).petProductDao().getProduct()
            if(showData == null){
                Toast.makeText(requireContext(),"No products yet",Toast.LENGTH_SHORT).show()
            }else{
                for(product in showData){
                    ProductList.add(
                        PetProductEntity(
                            product.id,
                            product._id,
                            product.productName,
                            product.productDescription,
                            product.productPrice,
                            product.productCategory,
                            product.productImage

                        )
                    )
                }
            }

        }
    }
}
