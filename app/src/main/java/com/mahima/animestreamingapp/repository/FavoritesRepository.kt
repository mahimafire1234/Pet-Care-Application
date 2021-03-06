package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.FavoritesApi
import com.mahima.animestreamingapp.api.MyApiRequest
import com.mahima.animestreamingapp.api.OrderApi
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.response.FavoritesResponse

class FavoritesRepository : MyApiRequest(){
//    repository for favorites
    private val favoritesApi = ServiceBuilder.buildService(FavoritesApi::class.java)

//    function to create favorites
    suspend fun addToFavorites(id:String,productId:String): FavoritesResponse{
        return apiRequest {
            favoritesApi.addToFavorites(ServiceBuilder.token!!,id,productId)
        }
    }

    //    function to get favorites
    suspend fun showFavorites(id:String): FavoritesResponse{
        return apiRequest {
            favoritesApi.getFavorites(ServiceBuilder.token!!,id)
        }
    }

    //    function to get favorites
    suspend fun deleteFavorites(id:String,productId: String): FavoritesResponse{
        return apiRequest {
            favoritesApi.deleteFavorites(ServiceBuilder.token!!,id,productId)
        }
    }

}