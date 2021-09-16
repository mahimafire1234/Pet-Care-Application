package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.entity.HireEntity

data class HireResponse(
    val success: Boolean ?= null,
    val data : HireEntity ?= null,
    val message : String ?= null
)
