package com.mahima.animestreamingapp.repository

import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.api.UserApi

//this file is for user repository
class UserRepository {
    private val userApi =ServiceBuilder.buildService(UserApi::class.java)
}