package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.response.HireGetResponse
import com.mahima.animestreamingapp.response.HireResponse
import retrofit2.Response
import retrofit2.http.*

interface HireApi {

//    to hire a pet care taker

    @FormUrlEncoded
    @POST("/hire")
    suspend fun hire(
        @Field("userId") userId :String,
        @Field("petcareId") petcaretakerId :String
    ):Response<HireResponse>

//    show pet care taker
    @GET("/hires/{id}")
    suspend fun getHire(
        @Path("id") id : String
    ):Response<HireGetResponse>

}