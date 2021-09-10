package com.mahima.animestreamingapp.entity

import java.util.*

data class OrderEntity(
    val userId : String ?= null,
    val product: List<ProductOrder> ?= null,
    val bill : Int =0,
    val date_added : Date ?= null,
    val delivery_address : String ?= null
)
