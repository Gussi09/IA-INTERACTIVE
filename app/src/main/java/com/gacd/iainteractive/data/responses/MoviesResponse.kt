package com.gacd.iainteractive.data.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("movies")
    val movies: List<Movy>,
    @SerializedName("routes")
    val routes: List<Route>
)