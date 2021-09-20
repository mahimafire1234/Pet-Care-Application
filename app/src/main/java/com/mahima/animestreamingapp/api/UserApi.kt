package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.response.UserGetResponse
import com.mahima.animestreamingapp.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

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

//    api for getting user
    @GET("/users/signInformation/{id}")
    suspend fun getUser(
        @Path("id") id :String
    ): Response<UserGetResponse>

//    update user information
    @FormUrlEncoded
    @PUT("/users/updateInformation/")
    suspend fun updateUser(
        @Field("id") id :String,
        @Field("fullName") fullName :String,
        @Field("email") email : String,
        @Field("password") password: String
    ):Response<UserResponse>

//    upload image
    @Multipart
    @PUT("/users/Imageuploadandroid/{id}")
    suspend fun uploadImage(
        @Path("id") id :String,
        @Part file:MultipartBody.Part
    ):Response<UserResponse>
}