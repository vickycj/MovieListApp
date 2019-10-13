package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.Movie
import com.vicky.apps.datapoints.ui.model.MovieList
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {




    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription():MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable

    private var movieList: List<Movie> =  ArrayList()


    fun getMovieList() = movieList

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }



    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy ( onSuccess = {
            movieList = it.movies
            response.postValue(true)
        }, onError = {
            Log.d("valuessss",it.message)
            response.postValue(false)
        } ))


    }
    fun generateApiCall():Single<MovieList>{
        return repository.getDataFromApi()
            .compose(schedulerProvider.getSchedulersForSingle())
    }







}