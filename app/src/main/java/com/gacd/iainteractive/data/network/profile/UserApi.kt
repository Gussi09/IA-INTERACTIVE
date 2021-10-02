package com.gacd.iainteractive.data.network.profile

import com.gacd.iainteractive.data.responses.User
import retrofit2.http.GET

interface UserApi {

    @GET("v1/members/profile?country_code=MX")

    suspend fun getUser(): User
}