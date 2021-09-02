package com.mahima.petcarewear.repository

import com.mahima.petcarewear.api.Myapirequest
import com.mahima.petcarewear.api.ServiceBuilder
import com.mahima.petcarewear.api.UserApi
import com.mahima.petcarewear.model.UserModel
import com.mahima.petcarewear.response.UserResponse

class UserRepository  : Myapirequest(){

    private val userApi = ServiceBuilder.buildService(UserApi::class.java)

    //    function for user register
    suspend fun userRegister(user : UserModel) : UserResponse {
        return apiRequest{
            userApi.RegisterUser(user)
        }
    }
    //    function for login
    suspend fun userLogin(email :String,password:String) : UserResponse{
        return apiRequest {
            userApi.LoginUser(email,password)
        }
    }
}