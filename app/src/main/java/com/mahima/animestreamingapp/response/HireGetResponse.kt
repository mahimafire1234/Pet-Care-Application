package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.HireEntity
import com.mahima.animestreamingapp.entity.PetCareTakerHire

data class HireGetResponse(
    val success : Boolean ?= null,
    val hiredITem : HireEntity?= null
)
