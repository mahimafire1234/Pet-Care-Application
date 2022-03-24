package com.mahima.animestreamingapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

//    base url
    private val BASE_URL = "http://192.168.1.80:80/"
//    private val BASE_URL = "http://172.25.0.243:80"
    var token : String ?= null
    var userId : String ?= null
//    create instance for retrofit
    private val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().build())
    .build()
//    generic function
    fun <T> buildService(serviceType : Class<T>) : T{
        return retrofitBuilder.create(serviceType)
    }

}