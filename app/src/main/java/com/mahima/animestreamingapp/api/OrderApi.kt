package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.response.OrderResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
//    api to make an order
    @FormUrlEncoded
    @POST("/order/{id}")
    suspend fun createOrder(
        @Path("id") id :String,
        @Field("payment") payment : String,
        @Field("delivery_address") delivery_address: String

    ):Response<OrderResponse>
}