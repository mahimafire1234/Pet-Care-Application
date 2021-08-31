package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.response.petCareTakerresponse
import com.mahima.animestreamingapp.response.petresponse
import retrofit2.http.GET

interface PetCareTakerApi {
//    api for getting data
    @GET("/petCareTakers")
    suspend fun getPetCareTaker():retrofit2.Response<petCareTakerresponse>
}