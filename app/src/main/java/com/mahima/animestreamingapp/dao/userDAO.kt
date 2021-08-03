package com.mahima.animestreamingapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mahima.animestreamingapp.entity.adminEntity

@Dao
interface userDAO {

//    write your queries here
//    register

    @Insert
    suspend fun register(user : adminEntity)

//    get user data
    @Query("Select * from adminEntity where username=(:username) and password=(:password)")
    suspend fun checkUser(username : String,password :String) :adminEntity
}