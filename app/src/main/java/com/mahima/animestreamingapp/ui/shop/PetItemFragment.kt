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
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.database.PetProductDatabase
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
    private lateinit var tv:TextView
    companion object{
        private lateinit var data:MutableList<PetProductModel>
        private lateinit var repository: PetProductRespository
        private lateinit var response:petresponse
        var alreadyExecuted = false
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
        tv=view.findViewById(R.id.tv)
//        get data code

//        check if has internet

        val ConnectionManager = view.context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkinfo = ConnectionManager.activeNetworkInfo

        CoroutineScope(Dispatchers.IO).launch {
            try {
//                gets data from repository
                repository = PetProductRespository()
                response = repository.showProducts()
                data = response.data!!
//                inserts data into room database
                insertRb()
                withContext(Main){
                    val showData=PetProductDatabase.getDatabase(view.context).petProductDao().getProduct()
                    tv.setText(showData.toString())
//                    if(networkinfo != null && networkinfo.isConnected ==true){
////                        if has internet sets the textview with api response data
//                        tv.setText(data.toString())

//                    }
                }
            }
            catch (ex:Exception){
////                if no internet gets the response data from api and inserts it to room db and shows in tv
//                    insertRb()
                withContext(Main){
//                    tv.setText(showData.toString())
//                    Toast.makeText(view.context,"room db",Toast.LENGTH_LONG).show()
                Toast.makeText(requireContext(),ex.toString(),Toast.LENGTH_SHORT).show()

                }
            }
        }
        return view
    }
//    function to insert data in room database
    private fun insertRb(){
        for(i in data){
            var insertData = PetProductEntity(
                productName = i.productName!!,
                productDescription = i.productDescription!!,
                productPrice = i.productPrice!!
            )
            PetProductDatabase.getDatabase(requireContext()!!).petProductDao().insertProduct(insertData)
        }
    }
}
