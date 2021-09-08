package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.response.ShoppingCartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ShoppingCartApi {
//    api for inserting items into cart
    @FormUrlEncoded
    @POST("/cart")
    suspend fun addToCart(

        @Field("userId") userId: String,
        @Field("productId") productId :String,
        @Field("quantity") quantity :Int

    ):Response<ShoppingCartResponse>


//    api for getting cart items
}