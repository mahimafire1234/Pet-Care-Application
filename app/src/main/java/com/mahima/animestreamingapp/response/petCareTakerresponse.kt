package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.PetCareTakerEntity
import com.mahima.animestreamingapp.model.PetCareTakerModel

data class petCareTakerresponse(
    val success : Boolean? = null,
    val data: ArrayList<PetCareTakerEntity> ?= null
)