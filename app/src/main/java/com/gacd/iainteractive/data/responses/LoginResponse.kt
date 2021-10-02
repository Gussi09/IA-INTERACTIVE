package com.gacd.iainteractive.data.responses

data class LoginResponse(
    val access_token: String,
    val country_code: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String,
    val username: String
)