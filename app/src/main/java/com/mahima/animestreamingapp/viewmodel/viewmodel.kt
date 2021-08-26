package com.mahima.animestreamingapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahima.animestreamingapp.entity.PetProductEntity
import com.mahima.animestreamingapp.repository.PetRepository
import kotlinx.coroutines.Deferred

class viewmodel : ViewModel(){
    companion object {
        private lateinit var repository: PetRepository
        private lateinit var petdetails: LiveData<List<PetProductEntity>>;

         fun insert(data: PetProductEntity) {
             repository = PetRepository(Application())
             repository.insert(data)

        }

        private fun petdetails(): LiveData<List<PetProductEntity>> {
            petdetails = repository.getpetdetails()
            return petdetails
        }
    }
}