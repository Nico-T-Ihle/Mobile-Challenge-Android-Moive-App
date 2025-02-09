package com.example.mobilechallange2023.DataModel


data class Director(
    val name: String,
    val pictureUrl: String
)

data class Movie(
    val title: String,
    val year: Int,
    val genre: List<String>?,
    val director: Director?,
    val actors: List<String>?,
    val plot: String?,
    val posterUrl: String,
    val rating: Float,
    val revenue: Int?,
    val releaseDate: String?,
    val runtime: Int?,
    val overview: String?,
    val reviews: Int?,
    val budget: Int?,
    val language: String?,
    val genres: List<String>?
)
