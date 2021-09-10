package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.OrderEntity

data class OrderResponse(
    val success : Boolean ?= null,
    val order : OrderEntity ?= null
)
