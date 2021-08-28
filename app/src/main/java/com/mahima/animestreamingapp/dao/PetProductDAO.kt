package com.mahima.animestreamingapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahima.animestreamingapp.entity.PetProductEntity

@Dao
interface PetProductDAO {
//    queries

//    insert product in room database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(petProductEntity: PetProductEntity)
//    query to get data
    @Query("Select * from petProduct")
    fun getProducts(): LiveData<List<PetProductEntity>>

    @Query("Select * from petProduct")
    suspend fun getProduct():List<PetProductEntity>
}