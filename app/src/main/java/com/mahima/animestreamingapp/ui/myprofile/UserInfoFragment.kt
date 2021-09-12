package com.mahima.animestreamingapp.ui.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserInfoFragment : Fragment() {

//    declaring variables
    private lateinit var imgprofile : ImageView
    private lateinit var fullName : TextView
    private lateinit var email : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_info, container, false)

//        initializing variables
        imgprofile = view.findViewById(R.id.imgprofile)
        fullName = view.findViewById(R.id.fullName)
        email = view.findViewById(R.id.email)
        getUser()
        return view
    }

//    get user details
    private fun getUser(){
//        coroutines
        CoroutineScope(Dispatchers.IO).launch {
            try {
               val repository = UserRepository()
               val response = repository.getUser(ServiceBuilder.userId!!)
               if(response.success == true){
                   val data = response.data!!

                   withContext(Dispatchers.Main){
                           if(data.image == null){
                               Glide.with(requireContext())
                                   .load("data:image/png;base64,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")
                                   .into(imgprofile)
                           }else{
                               Glide.with(requireContext())
                                   .load("http://192.168.1.80:80/"+data.image)
                                   .into(imgprofile)
                           }

                           fullName.text="Full Name:" + " " + data.fullName
                           email.text="Email:" + " " + data.email
                       }


               }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}