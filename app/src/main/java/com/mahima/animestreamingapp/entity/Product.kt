package com.mahima.animestreamingapp.entity

data class Product(
    var productId : String ?= null,
    var productName : String ?= null,
    var quantity : Int = 1,
    var productPrice : Int ?= null,
    var productImage : String?= null
)
