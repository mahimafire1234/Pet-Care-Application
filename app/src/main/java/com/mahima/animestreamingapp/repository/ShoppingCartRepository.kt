package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.ShoppingCartApi
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.response.ShoppingCartResponse
import com.mahima.animestreamingapp.response.petresponse

class ShoppingCartRepository:MyApiRequest() {

    private val shoppingCartApi = ServiceBuilder.buildService(ShoppingCartApi::class.java)

//    function to add items to cart
    suspend fun showProducts(shoppingCartEntity: ShoppingCartEntity): ShoppingCartResponse {
        return apiRequest {
            shoppingCartApi.addToCart(shoppingCartEntity)
        }
    }
}