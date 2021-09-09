package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.ShoppingCartEntity

data class ShoppingCartResponse(
    val success : Boolean?=null,
    val cart : ShoppingCartEntity ?= null
)