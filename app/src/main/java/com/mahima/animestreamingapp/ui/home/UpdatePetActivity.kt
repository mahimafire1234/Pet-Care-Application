package com.mahima.animestreamingapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.mahima.animestreamingapp.DashboardActivity
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForRecyclerView
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.entity.PetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UpdatePetActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private val listOfPets = arrayOf<String>("Dog","Cat")
    private lateinit var etpetname: EditText
    private lateinit var etpetage: EditText
    private lateinit var rbtnmale : RadioButton
    private lateinit var rbtnfemale : RadioButton
    private lateinit var addpet : Button
    private  var gender:String ="Male"
    private lateinit var petType:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_updatepet)
//initialization
        spinner=findViewById(R.id.spinner)
        etpetname = findViewById(R.id.etpetname)
        etpetage = findViewById(R.id.etpetage)
        rbtnmale = findViewById(R.id.rbtnmale)
        rbtnfemale = findViewById(R.id.rbtnfemale)
        addpet = findViewById(R.id.addpet)
//spinner adapter
        val spinner_adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            listOfPets
        )
        spinner.adapter=spinner_adapter

//get data for update
        val intent = intent.getParcelableExtra<PetEntity>("pet")
//set data in update form
        if (intent != null) {
            etpetname.setText(intent.petName)
            etpetage.setText(intent.petAge.toString())

            if (intent.petGender == "Male") {
                rbtnmale.isChecked = true
                gender = "Male"
            } else if (intent.petGender == "Female") {
                rbtnfemale.isChecked = true
                gender = "Female"
            }
            petType=intent.petType!!
            if (petType == "Dog"){
                spinner.setSelection(0)
            }else{
                spinner.setSelection(1)
            }
        }
//update btn click listener
        addpet.setOnClickListener {
//spinner for animal type
            spinner.onItemSelectedListener=
                object: AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        petType = parent?.getItemAtPosition(position).toString()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        petType ="Dog"
                    }
                }
//            gender selection
            when {
                rbtnmale.isChecked()-> { gender = "Male"}
                rbtnfemale.isChecked() -> { gender ="Female"}
            }
//            update
            val pet =
                PetEntity(
                    id=intent!!.id,
                    petName = etpetname.text.toString(),
                    petAge = etpetage.text.toString(),
                    petGender = gender,
                    petType = petType
                )
//update
            CoroutineScope(Dispatchers.IO).launch {
                PetProductDatabase.getDatabase(this@UpdatePetActivity)
                    .petDao()
                    .updatePet(pet)
                withContext(Main) {
                    startActivity(Intent(this@UpdatePetActivity,DashboardActivity::class.java))
                    Toast.makeText(this@UpdatePetActivity,"Pet updated successfully",Toast.LENGTH_SHORT).show()

                }

            }
        }
    }
}
