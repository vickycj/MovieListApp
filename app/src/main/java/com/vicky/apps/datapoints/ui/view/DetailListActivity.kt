package com.vicky.apps.datapoints.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DetailListAdapter
import com.vicky.apps.datapoints.ui.model.DetailData
import com.vicky.apps.datapoints.ui.model.DetailModel
import com.vicky.apps.datapoints.ui.viewmodel.DetailListViewModel
import kotlinx.android.synthetic.main.activity_detail_list.*
import javax.inject.Inject
import androidx.core.app.NavUtils

import android.view.MenuItem
import com.vicky.apps.datapoints.R


class DetailListActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:DetailListViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        loadValues()

        val intent: Intent =intent

        val list: ArrayList<DetailModel>

        val data: DetailData = intent.extras?.getSerializable(AppConstants.LIST_DATA) as DetailData

        list = data.list

        val poster = intent.extras?.getString(AppConstants.POSTER_DATA)
        val poster2 = intent.extras?.getString(AppConstants.POSTER_DATA2)

        initializeValues(
           list,poster, poster2)

        actionBar?.title = list[0].value
    }

    private fun initializeValues(data: ArrayList<DetailModel>?, poster: String?, poster2: String?) {
        recyclerView = bodyRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        adapter = DetailListAdapter(data!!)

        recyclerView.adapter = adapter

        when {
            isNotEmpty(poster!!) -> loadPosterData(poster, singleImageHolder)
            isNotEmpty(poster2!!) -> loadPosterData(poster2, singleImageHolder)
            else -> loadDefaultPoster(singleImageHolder)
        }

        movie_title.text = data[0].value
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
