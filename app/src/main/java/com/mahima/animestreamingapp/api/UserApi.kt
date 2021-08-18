package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

//this is the api for user log in and register
interface UserApi {
//    api for registering user
    @POST("users/signin")
    suspend fun RegisterUser(
        @Body user : UserModel
    ):Response<UserResponse>
}