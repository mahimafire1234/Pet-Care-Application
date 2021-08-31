package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.model.PetCareTakerModel

data class petCareTakerresponse(
    val success : Boolean? = null,
    val data: MutableList<PetCareTakerModel> ?= null
)