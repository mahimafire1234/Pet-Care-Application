package com.mahima.animestreamingapp.entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "petProduct")
data class PetProductEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("_id")
    var id : Int,

    @SerializedName("productName")
    var productName : String,

    @SerializedName("productDescription")
    var productDescription : String,

    @SerializedName("productPrice")
    var productPrice: Int
)
