package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.response.ShoppingCartResponse
import retrofit2.Response
import retrofit2.http.POST

interface ShoppingCartApi {
//    api for inserting items into cart
    @POST("/cart")
    suspend fun addToCart(shoppingCartEntity: ShoppingCartEntity):Response<ShoppingCartResponse>


//    api for getting cart items
}