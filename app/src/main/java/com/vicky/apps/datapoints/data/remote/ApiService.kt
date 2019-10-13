package com.vicky.apps.datapoints.data.remote


import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("/bins/18buhu")
    fun getDataFromService(): Single<List<Any>>
}
