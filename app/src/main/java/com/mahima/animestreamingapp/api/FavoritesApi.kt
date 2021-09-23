package com.mahima.animestreamingapp.api

import com.mahima.animestreamingapp.response.FavoritesResponse
import retrofit2.Response
import retrofit2.http.*

interface FavoritesApi {

//    function to add to favorites
    @FormUrlEncoded
    @POST("/addfavorites/{id}")
    suspend fun addToFavorites(
    @Header("Authorization") token:String,
    @Path("id") id : String,
        @Field("productId") productId : String
    ): Response<FavoritesResponse>

//    function to get favorites
    @GET("/getFavorites/{id}")
    suspend fun getFavorites(
    @Header("Authorization") token:String,
    @Path("id") id : String
    ):Response<FavoritesResponse>

//    function to remove item from favorites
    @DELETE("/deleteFavoritesItem/{id}/{productId}")
    suspend fun deleteFavorites(
    @Header("Authorization") token:String,
    @Path("id") id :String,
        @Path("productId") productId: String
    ):Response<FavoritesResponse>
}