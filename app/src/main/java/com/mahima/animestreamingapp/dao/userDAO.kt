package com.mahima.animestreamingapp.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mahima.animestreamingapp.entity.adminEntity

@Dao
interface userDAO {

//    write your queries here
//    register

    @Insert
    suspend fun register(user : adminEntity)
}