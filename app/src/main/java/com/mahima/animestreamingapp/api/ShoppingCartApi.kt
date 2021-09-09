package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.response.ShoppingCartResponse
import retrofit2.Response
import retrofit2.http.*

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
    @GET("/getCart/{id}")
    suspend fun showCart(
        @Path("id") id: String
    ): Response<ShoppingCartResponse>
}