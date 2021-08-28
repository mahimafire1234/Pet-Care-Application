package com.mahima.animestreamingapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mahima.animestreamingapp.entity.PetEntity

@Dao
interface PetDAO {
//    insert in room
    @Insert
    suspend fun insertPet(petEntity: PetEntity)

    @Query("Select * from PetEntity")
    suspend fun getPet() : List<PetEntity>

}