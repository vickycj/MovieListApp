package com.vicky.apps.datapoints.ui.view
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter

import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import javax.inject.Inject
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {



    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MainViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)
        initializeViewmodel()
        initializeRecyclerView()
        fetchDataFromApi()

    }

    private fun fetchDataFromApi() {
        viewModel.getDataFromRemote()
    }

    private fun initializeRecyclerView() {
        recyclerView = recycler_view

        recyclerView.layoutManager = GridLayoutManager(this, 3)

        adapter = DataAdapter(viewModel.getMovieList())

        recyclerView.adapter = adapter
    }


    private fun initializeViewmodel() {

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(this, Observer {
            if(it){
                successCallback()
            }else{
                failureCallback()
            }
        })

    }


    private fun successCallback(){
        updateData()
        Log.v("apival", viewModel.getMovieList().size.toString())
    }

    private fun updateData(){
       adapter.updateData(viewModel.getMovieList())
    }


    private fun failureCallback(){
        Log.v("api", "Api failed")
        Toast.makeText(this,"API failed",Toast.LENGTH_LONG).show()
    }






}
