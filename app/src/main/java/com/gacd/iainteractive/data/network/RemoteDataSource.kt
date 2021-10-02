package com.gacd.iainteractive.data.network

import com.gacd.iainteractive.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object{
        //Base URL to connect with EndPoints
        private const val BASE_URL = "https://stage-api.cinepolis.com/"
    }

    fun <Api> buildApi(
        api : Class<Api>,
        token: String? = null,
        tokenType: String? = null
    ) : Api{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            chain.proceed(chain.request().newBuilder().also {
                                it.addHeader("api_key","stage_HNYh3RaK_Test")
                                it.addHeader("Authorization", "$tokenType $token")
                            }.build())
                        }.also { client ->
                            if (BuildConfig.DEBUG){
                                val logging = HttpLoggingInterceptor()
                                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                                client.addInterceptor(logging)
                            }
                        }.build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)

    }
}