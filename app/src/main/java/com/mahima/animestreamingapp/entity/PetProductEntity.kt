package com.mahima.animestreamingapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "petProduct")
data class PetProductEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("_id")
    var id : Int=0,

    @SerializedName("productName")
    var productName : String,

    @SerializedName("productDescription")
    var productDescription : String,

    @SerializedName("productPrice")
    var productPrice: Int,

    @SerializedName("productCategory")
    var productCategory: String,

    @SerializedName("productImage")
    var productImage: String

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(productName)
        parcel.writeString(productDescription)
        parcel.writeInt(productPrice)
        parcel.writeString(productCategory)
        parcel.writeString(productImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PetProductEntity> {
        override fun createFromParcel(parcel: Parcel): PetProductEntity {
            return PetProductEntity(parcel)
        }

        override fun newArray(size: Int): Array<PetProductEntity?> {
            return arrayOfNulls(size)
        }
    }
}