package com.mahima.animestreamingapp.entity

data class ProductOrder(
    var productId : String ?= null,
    var productName : String ?= null,
    var quantity : Int?=null,
    var productPrice : Int ?= null,
)
