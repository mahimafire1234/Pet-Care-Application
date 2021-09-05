package com.mahima.animestreamingapp.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PetCareTaker",indices = [Index(value = arrayOf("_id"), unique = true)])
data class PetCareTakerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    @SerializedName("_id")
    val _id:String,
    @SerializedName("photo")
    val photo : String ?= null,
    @SerializedName("fullName")
    val fullName : String ?= null,
    @SerializedName("age")
    val age: Int?=null,
    @SerializedName("Bio")
    val Bio : String ?= null,
    @SerializedName("price")
    val price: Int?=null,
    @SerializedName("phoneNum")
    val phoneNum:Long?=null

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Long::class.java.classLoader) as? Long
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(_id)
        parcel.writeString(photo)
        parcel.writeString(fullName)
        parcel.writeValue(age)
        parcel.writeString(Bio)
        parcel.writeValue(price)
        parcel.writeValue(phoneNum)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PetCareTakerEntity> {
        override fun createFromParcel(parcel: Parcel): PetCareTakerEntity {
            return PetCareTakerEntity(parcel)
        }

        override fun newArray(size: Int): Array<PetCareTakerEntity?> {
            return arrayOfNulls(size)
        }
    }
}