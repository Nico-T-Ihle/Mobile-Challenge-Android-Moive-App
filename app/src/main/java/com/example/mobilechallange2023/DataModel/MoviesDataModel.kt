package com.example.mobilechallange2023.DataModel


data class Director(
    val name: String,
    val birthdate: String
)

data class Movie(
    val title: String,
    val year: Int,
    val genre: List<String>,
    val director: Director,
    val actors: List<String>,
    val plot: String,
    val posterUrl: String,
    val rating: Float
)
