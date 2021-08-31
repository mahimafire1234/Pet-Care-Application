package com.mahima.animestreamingapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahima.animestreamingapp.entity.PetCareTakerEntity
@Dao
interface PetCareTakerDAO {
//    queries
//    insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPetCareTaker(petcaretaker:PetCareTakerEntity)
//    get query

    @Query("Select * from PetCareTaker")
    suspend fun getPetCareTaker():List<PetCareTakerEntity>
}