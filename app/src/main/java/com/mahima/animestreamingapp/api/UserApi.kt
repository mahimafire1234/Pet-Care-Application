package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//this is the api for user log in and register
interface UserApi {
//    api for registering user
    @POST("users/signin")
    suspend fun RegisterUser(
        @Body user : UserModel
    ):Response<UserResponse>
//    api for signning up users
    @FormUrlEncoded
    @POST("login")
    suspend fun LoginUser(
        @Field("email") email: String ,
        @Field("password") password :String
    ):Response<UserResponse>
}