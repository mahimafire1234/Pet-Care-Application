package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.response.ShoppingCartDeleteResponse
import com.mahima.animestreamingapp.response.ShoppingCartResponse
import retrofit2.Response
import retrofit2.http.*

interface ShoppingCartApi {
//    api for inserting items into cart
    @FormUrlEncoded
    @POST("/cart")
    suspend fun addToCart(
        @Header("Authorization") token:String,
        @Field("userId") userId: String,
        @Field("productId") productId :String,
        @Field("quantity") quantity :Int

    ):Response<ShoppingCartResponse>

//    api for getting cart items
    @GET("/getCart/{id}")
    suspend fun showCart(
    @Header("Authorization") token:String,
    @Path("id") id: String
    ): Response<ShoppingCartResponse>

//    api to delete cart item
    @DELETE("/deleteitem/{id}/{itemid}")
    suspend fun deleteCart(
        @Header("Authorization") token:String,
        @Path("id") id :String,
        @Path("itemid") itemid :String
    ):Response<ShoppingCartDeleteResponse>
}