package com.gacd.iainteractive.data.repositories

import com.gacd.iainteractive.data.network.profile.UserApi

//Class that requests User Info From UserAPI

class UserRepository(
    private val api: UserApi
): BaseRepository() {
    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}