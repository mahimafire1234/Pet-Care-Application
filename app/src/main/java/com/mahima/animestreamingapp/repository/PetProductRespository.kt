package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.PetProductApi
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.UserApi
import com.mahima.animestreamingapp.model.PetProductModel
import com.mahima.animestreamingapp.response.petresponse

class PetProductRespository:MyApiRequest() {
    private val petApi = ServiceBuilder.buildService(PetProductApi::class.java)

    suspend fun showProducts():petresponse{
        return apiRequest {
            petApi.getProduct()
        }
    }
}