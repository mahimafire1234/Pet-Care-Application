package com.mahima.animestreamingapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mahima.animestreamingapp.entity.PetProductEntity

@Dao
interface PetProductDAO {
//    queries

//    insert product in room database
    @Insert
    suspend fun insertProduct(petProductEntity: PetProductEntity)
//    query to get data
    @Query("Select * from petProduct")
    suspend fun getProduct(): LiveData<List<PetProductEntity>>

}