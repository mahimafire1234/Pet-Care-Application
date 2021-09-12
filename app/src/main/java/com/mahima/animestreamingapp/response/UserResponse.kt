package com.mahima.animestreamingapp.response

import com.mahima.animestreamingapp.model.UserModel

//this data class is for the repsonse
data class UserResponse (
     val success : Boolean?= null,
     val token: String?= null,
     val message :String?=null,
     val userId : String?=null
)