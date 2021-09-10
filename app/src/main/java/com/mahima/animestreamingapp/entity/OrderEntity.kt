package com.mahima.animestreamingapp.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class OrderEntity(
    val userId : String ?= null,
    val product: List<ProductOrder> ?= null,
    val bill : Int =0,
    val date_added : String ?= null,
    val delivery_address : String ?= null
)