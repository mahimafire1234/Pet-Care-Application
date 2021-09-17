package com.mahima.petcarewear.model

data class PetProductModel(
    var _id :String = "",
    var productName : String ?= null,
    var productDescription : String ?= null,
    var productPrice : Int ?= null,
    var productCategory : String?= null,
    var productImage:String?= null
)
