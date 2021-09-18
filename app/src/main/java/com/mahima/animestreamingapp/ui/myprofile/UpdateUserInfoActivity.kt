package com.mahima.animestreamingapp.ui.myprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.repository.UserRepository
import com.mahima.animestreamingapp.ui.AboutusFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UpdateUserInfoActivity : AppCompatActivity() {
//    declaring variables
    private lateinit var etusernameupdate : EditText
    private lateinit var etemailupdate : EditText
    private lateinit var etpasswordupdate : EditText
    private lateinit var etconfirmpasswordupdate : EditText
    private lateinit var btnupdateuserinfo : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_info)

//        initializing
        etusernameupdate = findViewById(R.id.etusernameupdate)
        etemailupdate = findViewById(R.id.etemailupdate)
        etpasswordupdate = findViewById(R.id.etpasswordupdate)
        etconfirmpasswordupdate =findViewById(R.id.etconfirmpasswordupdate)
        btnupdateuserinfo = findViewById(R.id.btnupdateuserinfo)

//        get intent
        val email = intent.getStringExtra("email")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

//        set variables
        etusernameupdate.setText(username)
        etemailupdate.setText(email)

        btnupdateuserinfo.setOnClickListener { update() }

    }
    private fun update(){
        val fullname = etusernameupdate.text.toString()
        val email = etemailupdate.text.toString()
        val updatepassword = etpasswordupdate.text.toString()
        val newpassword = etconfirmpasswordupdate.text.toString()

        if(etconfirmpasswordupdate.text.isEmpty()){
            etconfirmpasswordupdate.error = "Please type your new passsword"
            etconfirmpasswordupdate.requestFocus()
        }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = UserRepository()
                    val response = repository.updateUser(ServiceBuilder.userId!!,fullName = fullname,email = email,password = newpassword)
                    if(response.success == true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@UpdateUserInfoActivity,"Your user info has been updates successfully",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                catch (ex:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UpdateUserInfoActivity,ex.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}