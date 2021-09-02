package com.mahima.petcarewear.api

import com.mahima.petcarewear.model.UserModel
import com.mahima.petcarewear.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    //    api for registering user
    @POST("users/signin")
    suspend fun RegisterUser(
        @Body user : UserModel
    ): Response<UserResponse>
    //    api for signning up users
    @FormUrlEncoded
    @POST("login")
    suspend fun LoginUser(
        @Field("email") email: String,
        @Field("password") password :String
    ): Response<UserResponse>
}