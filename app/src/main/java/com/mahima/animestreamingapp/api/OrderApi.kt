package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.response.OrderResponse
import com.mahima.animestreamingapp.response.ShowOrderResponse
import retrofit2.Response
import retrofit2.http.*

interface OrderApi {
//    api to make an order
    @FormUrlEncoded
    @POST("/order/{id}")
    suspend fun createOrder(
        @Header("Authorization") token:String,
        @Path("id") id :String,
        @Field("payment") payment : Long,
        @Field("delivery_address") delivery_address: String

    ):Response<OrderResponse>

//    api to show order
    @GET("/getOrder/{id}")
    suspend fun showOrder(
    @Header("Authorization") token:String,
    @Path("id") id: String
    ):Response<ShowOrderResponse>
}