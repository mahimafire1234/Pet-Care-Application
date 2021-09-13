package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.ShoppingCartApi
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.response.ShoppingCartDeleteResponse
import com.mahima.animestreamingapp.response.ShoppingCartResponse
import com.mahima.animestreamingapp.response.petresponse

class ShoppingCartRepository:MyApiRequest() {

    private val shoppingCartApi = ServiceBuilder.buildService(ShoppingCartApi::class.java)

//    function to add items to cart
    suspend fun addProducts(userId:String,productId:String,quantity:Int): ShoppingCartResponse {
        return apiRequest {
            shoppingCartApi.addToCart(ServiceBuilder.token!!,userId,productId,quantity)
        }
    }
//    function to get cart items
    suspend fun getProduct(id: String) : ShoppingCartResponse{
        return apiRequest {
            shoppingCartApi.showCart(ServiceBuilder.token!!,id)
        }
    }

//    function to delete item from cart
    suspend fun deleteItem(id:String,itemid:String) : ShoppingCartDeleteResponse{
        return apiRequest {
            shoppingCartApi.deleteCart(ServiceBuilder.token!!,id,itemid)
        }
    }
}