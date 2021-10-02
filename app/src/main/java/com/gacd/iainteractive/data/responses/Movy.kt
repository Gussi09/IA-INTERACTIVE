package com.gacd.iainteractive.data.responses

data class Movy(
    val cast: List<Cast>,
    val categories: List<String>,
    val cinemas: List<Any>,
    val code: String,
    val distributor: String,
    val genre: String,
    val id: Int,
    val length: String,
    val media: List<Media>,
    val name: String,
    val original_name: String,
    val position: Int,
    val rating: String,
    val release_date: String,
    val synopsis: String
)