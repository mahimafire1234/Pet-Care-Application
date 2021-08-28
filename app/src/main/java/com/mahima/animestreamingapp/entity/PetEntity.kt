package com.mahima.animestreamingapp.entity

import android.os.Parcel
import android.os.Parcelable
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
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(petName)
        parcel.writeString(petType)
        parcel.writeString(petAge)
        parcel.writeString(petGender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PetEntity> {
        override fun createFromParcel(parcel: Parcel): PetEntity {
            return PetEntity(parcel)
        }

        override fun newArray(size: Int): Array<PetEntity?> {
            return arrayOfNulls(size)
        }
    }
}