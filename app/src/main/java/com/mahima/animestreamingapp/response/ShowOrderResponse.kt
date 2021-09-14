package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.OrderEntity

data class ShowOrderResponse(
    val success : Boolean ?= null,
    val order : ArrayList<OrderEntity>?= null
)
