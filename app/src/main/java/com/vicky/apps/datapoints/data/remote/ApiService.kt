package com.vicky.apps.datapoints.data.remote


import com.vicky.apps.datapoints.ui.model.MovieList
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("/bins/18buhu")
    fun getDataFromService(): Single<MovieList>
}
