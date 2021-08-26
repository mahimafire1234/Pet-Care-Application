package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.model.PetProductModel
import com.mahima.animestreamingapp.response.petresponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PetProductApi {
//    api for getting data
    @GET("product")
    suspend fun getProduct():Response<petresponse>
    fun getProductAsync():Deferred<Response<List<PetProductModel>>>
}