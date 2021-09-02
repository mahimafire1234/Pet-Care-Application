package com.mahima.petcarewear.model

data class UserModel(
    //    fields
    var _id : String="",
    var fullName : String ?= null,
    val email : String ?= null,
    var image : String ?= null,
    var password : String ?= null
)

