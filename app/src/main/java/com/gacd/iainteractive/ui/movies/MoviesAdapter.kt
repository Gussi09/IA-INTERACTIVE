package com.gacd.iainteractive.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gacd.iainteractive.R
import com.gacd.iainteractive.data.responses.Movy
import com.gacd.iainteractive.data.responses.Route
import com.gacd.iainteractive.databinding.RecyclerviewMovieBinding

class MoviesAdapter(
    private val movies: List<Movy>,
    private val route: List<Route>
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    override fun getItemCount() = movies.size

    //Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_movie,
                parent,
                false
            )
        )
    //Called by RecyclerView to display the data at the specified position. This method should update the contents of the RecyclerView.ViewHolder.itemView to reflect the item at the given position.

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.movie = movies[position]
        holder.recyclerviewMovieBinding.route = route[3]

    }

    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: RecyclerviewMovieBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)

}