package com.mahima.petcarewear.api

import com.mahima.petcarewear.response.petresponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("product")
    suspend fun getProduct(): Response<petresponse>
}