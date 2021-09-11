package com.mahima.animestreamingapp.entity

data class FavoritesEntity(
    val product : List<ProductFavorites> ?= null,
    val userId : String ?= null
)
