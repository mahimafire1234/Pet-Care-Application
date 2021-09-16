package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.FavoritesApi
import com.mahima.animestreamingapp.api.HireApi
import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.response.HireGetResponse
import com.mahima.animestreamingapp.response.HireResponse

class HireRepository : MyApiRequest(){

    private val hireApi = ServiceBuilder.buildService(HireApi::class.java)

//    hire
    suspend fun hire(userId:String,petcareId:String):HireResponse{
        return apiRequest{
            hireApi.hire(userId,petcareId)
        }
    }

//    show hires
    suspend fun shoeHires(id:String):HireGetResponse{
        return apiRequest {
            hireApi.getHire(id)
        }
    }

}