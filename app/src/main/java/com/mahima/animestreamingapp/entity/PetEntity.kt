package com.mahima.animestreamingapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PetEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val petName:String ?= null,
    val petType:String ?= null,
    val petAge:String ?= null,
    val petGender:String ?= null,
    )