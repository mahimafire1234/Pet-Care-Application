package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.FavoritesEntity

data class FavoritesResponse(
    val success : Boolean ?= null,
    val favoriteItem : FavoritesEntity ?= null
)
