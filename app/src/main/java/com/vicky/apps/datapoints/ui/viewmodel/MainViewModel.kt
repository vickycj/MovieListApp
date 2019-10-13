package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.DetailModel
import com.vicky.apps.datapoints.ui.model.Movie
import com.vicky.apps.datapoints.ui.model.MovieList
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {


    private val keyDefaults = arrayOf("Title","Year","Rated","Genre","Actors","Plot","Language","Country","Released","Runtime","Director","Writer","Awards")

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

    fun frameData(data:Movie): ArrayList<DetailModel> {
        val movieDetail = ArrayList<DetailModel>()
        movieDetail.add(DetailModel(keyDefaults[0],data.title))
        movieDetail.add(DetailModel(keyDefaults[1],data.year))
        movieDetail.add(DetailModel(keyDefaults[2],data.rated))
        movieDetail.add(DetailModel(keyDefaults[3],data.genre))
        movieDetail.add(DetailModel(keyDefaults[4],data.actors))
        movieDetail.add(DetailModel(keyDefaults[5],data.plot))
        movieDetail.add(DetailModel(keyDefaults[6],data.language))
        movieDetail.add(DetailModel(keyDefaults[7],data.country))
        movieDetail.add(DetailModel(keyDefaults[8],data.released))
        movieDetail.add(DetailModel(keyDefaults[9],data.runtime))
        movieDetail.add(DetailModel(keyDefaults[10],data.director))
        movieDetail.add(DetailModel(keyDefaults[11],data.writer))
        movieDetail.add(DetailModel(keyDefaults[12],data.awards))

        return movieDetail
    }


}