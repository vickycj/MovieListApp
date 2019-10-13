package com.vicky.apps.datapoints.ui.adapter


import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.Movie


class DataAdapter constructor(var data: List<Movie>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        when {
            isNotEmpty(data[position].poster) -> loadPosterData(data[position].poster, holder.posterImage)
            isNotEmpty(data[position].poster2) -> loadPosterData(data[position].poster2, holder.posterImage)
            else -> loadDefaultPoster(holder.posterImage)
        }

        holder.movieName.text = data[position].title

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

    fun updateData(newData: List<Movie>){
        data = newData
        notifyDataSetChanged()
    }
    inner class DataViewHolder(v:View): RecyclerView.ViewHolder(v){
        val posterImage: ImageView = v.findViewById(R.id.imageView)
        val movieName: TextView = v.findViewById(R.id.textView)

        init {
            v.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}