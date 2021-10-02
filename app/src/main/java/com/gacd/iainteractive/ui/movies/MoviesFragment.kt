package com.gacd.iainteractive.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gacd.iainteractive.data.network.movies.MoviesApi
import com.gacd.iainteractive.data.repositories.MoviesRepository
import com.gacd.iainteractive.databinding.FragmentMoviesBinding
import com.gacd.iainteractive.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment<MoviesViewModel,FragmentMoviesBinding,MoviesRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        //Load RecyclerView Movies in MoviesAdapter
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->

            recycler_view_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MoviesAdapter(movies.movies,movies.routes)
            }

        })

    }
    override fun getViewModel() = MoviesViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoviesBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): MoviesRepository {

        val api = remoteDataSource.buildApi(MoviesApi::class.java, null, null)

        return MoviesRepository(api)
    }
}