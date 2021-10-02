package com.gacd.iainteractive.data.repositories

import com.gacd.iainteractive.data.network.movies.MoviesApi

//Class that requests User Info From MoviesAPI

class MoviesRepository(
    private val api : MoviesApi
): BaseRepository() {
    suspend fun getMovies() = apiRequest {
        api.getMovies()
    }
}