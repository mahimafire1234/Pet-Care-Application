package com.mahima.animestreamingapp.entity

import java.util.*
import kotlin.collections.ArrayList

data class ShoppingCartEntity(
    var userId : String ?= null,
    var product:List<Product> ? =null,
    var bill: Int = 0
)
