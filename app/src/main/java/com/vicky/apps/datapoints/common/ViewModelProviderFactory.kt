package com.vicky.apps.datapoints.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.viewmodel.DetailListViewModel
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import javax.inject.Inject


class ViewModelProviderFactory @Inject constructor( var repository: Repository,  var schedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            return MainViewModel(repository, schedulerProvider) as T
        }else if (modelClass.isAssignableFrom(DetailListViewModel::class.java)){
            return DetailListViewModel(repository, schedulerProvider) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName())
    }
}