package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.model.UserModel

data class UserGetResponse(
    val success: Boolean ?= null,
    val data : UserModel ?= null
)
