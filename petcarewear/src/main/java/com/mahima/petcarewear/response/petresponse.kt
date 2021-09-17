package com.mahima.petcarewear.response

import com.mahima.petcarewear.model.PetProductModel

data class petresponse(
    val success: Boolean ?= null,
    val data :ArrayList<PetProductModel> ?=null
)
