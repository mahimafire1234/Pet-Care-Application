package com.mahima.petcarewear.repository

import com.mahima.petcarewear.api.Myapirequest
import com.mahima.petcarewear.api.ProductApi
import com.mahima.petcarewear.api.ServiceBuilder
import com.mahima.petcarewear.response.petresponse

class Petrepository: Myapirequest() {
    private val petApi = ServiceBuilder.buildService(ProductApi::class.java)

    suspend fun showProducts(): petresponse {
        return apiRequest {
            petApi.getProduct()
        }
    }
}