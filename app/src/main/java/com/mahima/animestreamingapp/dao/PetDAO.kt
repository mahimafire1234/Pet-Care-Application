package com.mahima.animestreamingapp.dao

import androidx.room.*
import com.mahima.animestreamingapp.entity.PetEntity
import retrofit2.http.DELETE

@Dao
interface PetDAO {
//    insert in room
    @Insert
    suspend fun insertPet(petEntity: PetEntity)

    @Query("Select * from PetEntity")
    suspend fun getPet() : List<PetEntity>

//    delete pet
    @Delete
    suspend fun deletePet(petEntity: PetEntity)

    @Update
    suspend fun updatePet(petEntity: PetEntity )

}