package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.UserApi
import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.response.UserResponse
import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.response.UserGetResponse


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
    suspend fun userLogin(email :String,password:String) : UserResponse{
        return apiRequest {
            userApi.LoginUser(email,password)
        }
    }

//    function for get user
    suspend fun getUser(id:String):UserGetResponse{
        return apiRequest {
            userApi.getUser(id)
        }
    }
}