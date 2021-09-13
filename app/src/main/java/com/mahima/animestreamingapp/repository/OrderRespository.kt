package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.OrderApi
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.ShoppingCartApi
import com.mahima.animestreamingapp.response.OrderResponse

class OrderRespository : MyApiRequest() {

    private val orderCartApi = ServiceBuilder.buildService(OrderApi::class.java)

//    function to make an order
    suspend fun createOrder(id : String,payment:String,delivery_address : String) : OrderResponse{
        return apiRequest {
            orderCartApi.createOrder(ServiceBuilder.token!!,id,payment, delivery_address)
        }
    }

//    function to show order
    suspend fun showOrder(id:String) :OrderResponse{
        return apiRequest {
            orderCartApi.showOrder(ServiceBuilder.token!!,id)
        }
    }

}