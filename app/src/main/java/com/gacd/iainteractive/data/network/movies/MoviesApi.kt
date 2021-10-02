package com.gacd.iainteractive.data.network.movies

import com.gacd.iainteractive.data.responses.MoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface MoviesApi {
    @GET("v2/movies?country_code=MX&cinema=61")

    suspend fun getMovies(): Response<MoviesResponse>

}