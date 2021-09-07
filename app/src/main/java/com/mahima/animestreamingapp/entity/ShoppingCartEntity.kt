package com.mahima.animestreamingapp.entity

import java.util.*
import kotlin.collections.ArrayList

data class ShoppingCartEntity(
    var userId : String ?= null,
    var productId : String ?=null,
    var productName : String ?= null,
    var quantity : Int =1,
    var productPrice : Int ?= null,
    var productImage : String ? =null,
    var bill: Int? = null
)
