package com.gacd.iainteractive.data.network.auth

import com.gacd.iainteractive.data.responses.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {
    @FormUrlEncoded
    @POST("v2/oauth/token")

    suspend fun userLogin(
        @Field("country_code") country_code: String,
        @Field("username") username: String,
        @Field("password") password : String,
        @Field("grant_type") grant_type : String,
        @Field("client_id") client_id : String,
        @Field("client_secret") client_secret : String

    ): LoginResponse

}
