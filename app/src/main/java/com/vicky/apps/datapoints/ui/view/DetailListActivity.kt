package com.vicky.apps.datapoints.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DetailListAdapter
import com.vicky.apps.datapoints.ui.model.DetailData
import com.vicky.apps.datapoints.ui.model.DetailModel
import com.vicky.apps.datapoints.ui.viewmodel.DetailListViewModel
import kotlinx.android.synthetic.main.activity_detail_list.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class DetailListActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:DetailListViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list)

        loadValues()

        var intent: Intent =intent

        var list: ArrayList<DetailModel>

        var data: DetailData = intent?.extras?.getSerializable(AppConstants.LIST_DATA) as DetailData

        list = data.list

        val poster = intent?.extras?.getString(AppConstants.POSTER_DATA)

        initializeValues(
           list,poster)
    }

    private fun initializeValues(data: ArrayList<DetailModel>?, poster: String?) {
        recyclerView = bodyRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        adapter = DetailListAdapter(data!!)

        recyclerView.adapter = adapter

        if (isNotEmpty(poster!!)){
            loadPosterData(
                poster,singleImageHolder)
        }else{
            loadDefaultPoster(singleImageHolder)
        }

    }

    private fun loadValues() {
        viewModel = ViewModelProviders.of(this, factory).get(DetailListViewModel::class.java)

    }

    private fun loadPosterData(poster: String,imageView: ImageView) {
        Picasso.get().load(poster).placeholder(R.drawable.default_movie).error(R.drawable.default_movie).into(imageView)
    }

    private fun loadDefaultPoster(imageView: ImageView){
        imageView.setImageResource(R.drawable.default_movie)
    }

    fun isNotEmpty(data:String): Boolean {
        return (!TextUtils.isEmpty(data))
    }
}
