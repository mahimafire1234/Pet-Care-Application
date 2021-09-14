package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.OrderApi
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.ShoppingCartApi
import com.mahima.animestreamingapp.response.OrderResponse
import com.mahima.animestreamingapp.response.ShowOrderResponse

class OrderRespository : MyApiRequest() {

    private val orderCartApi = ServiceBuilder.buildService(OrderApi::class.java)

//    function to make an order
    suspend fun createOrder(id : String,payment:Long,delivery_address : String) : OrderResponse{
        return apiRequest {
            orderCartApi.createOrder(ServiceBuilder.token!!,id,payment,delivery_address)
        }
    }

//    function to show order
    suspend fun showOrder(id:String) :ShowOrderResponse{
        return apiRequest {
            orderCartApi.showOrder(ServiceBuilder.token!!,id)
        }
    }

}