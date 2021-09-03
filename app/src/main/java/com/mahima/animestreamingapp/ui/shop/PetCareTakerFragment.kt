package com.mahima.animestreamingapp.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterforPetCareTaker
import com.mahima.animestreamingapp.database.PetCareDatabase
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.entity.PetCareTakerEntity
import com.mahima.animestreamingapp.model.PetCareTakerModel
import com.mahima.animestreamingapp.repository.PetCareTakerRepository
import com.mahima.animestreamingapp.response.petCareTakerresponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PetCareTakerFragment : Fragment() {

//    companion object of variables
    companion object{
        private lateinit var data:MutableList<PetCareTakerModel>
        private lateinit var repository:PetCareTakerRepository
        private lateinit var response:petCareTakerresponse
        val PetCareList : ArrayList<PetCareTakerEntity> = ArrayList<PetCareTakerEntity>()
    }
    private lateinit var recyclerview:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pet_care_taker, container, false)
//        recyclerview
        recyclerview=view.findViewById(R.id.recyclerview)
        val adapter = adapterforPetCareTaker(requireContext(), PetCareList)


//        getting data from api
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository= PetCareTakerRepository()
                response = repository.getPetCareTaker()
                data= response.data!!

//                function call for insertion
                insertInRoomDb()
//                show recyclerview
                withContext(Main){

                    recyclerview.layoutManager= GridLayoutManager(requireContext(),2)
                    recyclerview.adapter=adapter
                    PetCareList.clear()
                    getDataFromRoomDb()
                }
            }
            catch (ex : Exception){
                withContext(Main){
                    Toast.makeText(
                        view.context,
                        ex.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }
//    function to add in room database
    private fun insertInRoomDb(){
    CoroutineScope(Dispatchers.IO).launch {
        for(i in data){
            var dataToInsert = PetCareTakerEntity(
                fullName = i.fullName,
                age = i.age,
                Bio = i.Bio,
                price = i.price,
                phoneNum = i.phoneNum,
                photo = i.photo
            )
            PetCareDatabase.getDatabase(requireContext()).petCareDao().insertPetCareTaker(dataToInsert)

        }
    }

    }
//    function to get data from room database
    private fun getDataFromRoomDb(){
        CoroutineScope(Dispatchers.IO).launch {
            val showData = PetCareDatabase.getDatabase(requireContext()).petCareDao().getPetCareTaker()
            if(showData == null){
                Toast.makeText(requireContext(),"No care takers yet",Toast.LENGTH_SHORT ).show()
            }
            else{
                for(i in showData){
                    PetCareList.add(
                        PetCareTakerEntity(
                            id = i.id,
                            fullName = i.fullName,
                            price = i.price,
                            Bio = i.Bio,
                            age = i.age,
                            phoneNum = i.phoneNum,
                            photo = i.photo
                        )
                    )
                }
            }
        }
    }

}