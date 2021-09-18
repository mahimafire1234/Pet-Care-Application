package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.UserApi
import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.response.UserResponse
import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.response.UserGetResponse
import okhttp3.MultipartBody


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

//    function for update user
    suspend fun updateUser(id:String,fullName:String,email: String,password: String):UserResponse{
        return apiRequest {
            userApi.updateUser(id,fullName, email, password)
        }
    }

//    function for image upload
    suspend fun uploadImage(id:String,body:MultipartBody.Part) : UserResponse{
        return apiRequest {
            userApi.uploadImage(id,body)
        }
    }
}