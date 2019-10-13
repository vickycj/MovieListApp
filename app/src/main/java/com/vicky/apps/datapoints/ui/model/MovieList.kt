package com.vicky.apps.datapoints.ui.model


import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("movies")
    var movies: List<Movie> = listOf()
)