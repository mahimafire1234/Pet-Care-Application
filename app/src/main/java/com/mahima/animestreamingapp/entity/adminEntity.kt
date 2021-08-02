package com.mahima.animestreamingapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class adminEntity (
    @PrimaryKey (autoGenerate = true)
    val userId: Int = 0,
    val username: String ? = null,
    val email :String ? =null,
    val password : String ? =null

)