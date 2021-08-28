package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.model.PetProductModel

data class petresponse (
    val success: Boolean ?= null,
    val data :MutableList<PetProductModel> ?=null

    )