package com.gacd.iainteractive.data.repositories

import com.gacd.iainteractive.data.UserPreferences
import com.gacd.iainteractive.data.network.auth.AuthAPI

//Class that requests authentication by AuthAPI

class AuthRepository(
    private val api: AuthAPI,
    private val preferences: UserPreferences
): BaseRepository() {

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.userLogin("MX",username,password,"password","IATestCandidate","c840457e777b4fee9b510fbcd4985b68")
    }

    suspend fun saveAuthToken(token: String,tokenType: String){
        preferences.saveAuthToken(token)
        preferences.saveTokenType(tokenType)
    }
}