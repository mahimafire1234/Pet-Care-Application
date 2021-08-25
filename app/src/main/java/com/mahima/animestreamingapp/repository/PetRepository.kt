package com.mahima.animestreamingapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.mahima.animestreamingapp.dao.PetProductDAO
import com.mahima.animestreamingapp.database.PetProductDatabase
import com.mahima.animestreamingapp.entity.PetProductEntity

class PetRepository(application:Application) {
//    dao
    private lateinit var petProductDAO: PetProductDAO
//    pet details
    private lateinit var petdetails: LiveData<List<PetProductEntity>>
//    init
    init {
        val database: PetProductDatabase = PetProductDatabase.getDatabase(application)
        if(database != null){
            petProductDAO = database.petProductDao()
        }
        petdetails = petProductDAO.getProduct()
    }
//    insert function
    fun insert(petdatabase : PetProductEntity){
        val insertPetproductAsyncTask = InsertPetproductAsyncTask(petProductDAO).execute(petdatabase)
    }

    fun getpetdetails():LiveData<List<PetProductEntity>>{
        return petdetails
    }

//    class
   private class InsertPetproductAsyncTask(petProductDAO: PetProductDAO):AsyncTask<PetProductEntity,Unit,Unit>(){
       val petProductDAO = petProductDAO
       override fun doInBackground(vararg params: PetProductEntity?) {
           petProductDAO.insertProduct(params[0]!!)
       }

    }
}