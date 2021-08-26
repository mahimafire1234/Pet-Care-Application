package com.mahima.animestreamingapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.entity.PetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddPetFragment : Fragment() {

    private lateinit var spinner: Spinner
    private val listOfPets = arrayOf<String>("Dog","Cat")
    private lateinit var etpetname:EditText
    private lateinit var etpetage:EditText
    private lateinit var rbtnmale :RadioButton
    private lateinit var rbtnfemale :RadioButton
    private lateinit var addpet :Button
    private lateinit var petType:String
    private  var gender:String ="Male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add_pet, container, false)
//        binding
        spinner=view.findViewById(R.id.spinner)
        etpetname=view.findViewById(R.id.etpetname)
        etpetage=view.findViewById(R.id.etpetage)
        rbtnmale=view.findViewById(R.id.rbtnmale)
        rbtnfemale=view.findViewById(R.id.rbtnfemale)
        addpet=view.findViewById(R.id.addpet)

//        for spinner
        val spinner_adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_expandable_list_item_1,
            listOfPets
        )
        spinner.adapter=spinner_adapter
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

        addpet.setOnClickListener {
            //        get the data to insert
            val petname = etpetname.text.toString()
            val petage = etpetage.text.toString()
            when {
                rbtnmale.isChecked()-> { gender = "Male"}
                rbtnfemale.isChecked() -> { gender ="Female"}
            }
            val pet = PetEntity(
                petName = petname,
                petAge = petage,
                petGender = gender,
                petType = petType
            )
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    PetProductDatabase.getDatabase(view.context).petDao().insertPet(pet)
                    withContext(Main){
                        Toast.makeText(
                            view.context,
                            "Pet added successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            catch (ex:Exception){
                Toast.makeText(view.context,ex.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun check(){
    }
}