package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.UserApi
import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.response.UserResponse
import com.mahima.animestreamingapp.api.MyApiRequest


//this file is for user repository
class UserRepository  : MyApiRequest(){

    private val userApi =ServiceBuilder.buildService(UserApi::class.java)

//    function for user register
    suspend fun userRegister(user : UserModel) : UserResponse{
        return apiRequest{
            userApi.RegisterUser(user)
        }
    }
//    function for login
    suspend fun userLogin(username :String,password:String) : UserResponse{
        return apiRequest {
            userApi.LoginUser(username,password)
        }
    }
}