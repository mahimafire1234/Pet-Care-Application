package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.PetCareTakerApi
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.response.petCareTakerresponse

class PetCareTakerRepository:MyApiRequest() {

//
    private val petCareapi = ServiceBuilder.buildService(PetCareTakerApi::class.java)

//    function to get pet care taker
    suspend fun getPetCareTaker():petCareTakerresponse{
        return apiRequest {
            petCareapi.getPetCareTaker()
        }
    }
}