package com.gacd.iainteractive.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gacd.iainteractive.data.Coroutines
import com.gacd.iainteractive.data.repositories.MoviesRepository
import com.gacd.iainteractive.data.responses.MoviesResponse
import kotlinx.coroutines.Job

class MoviesViewModel(
    private val repository: MoviesRepository
    ) : ViewModel() {
    private lateinit var job : Job
    private val _movies = MutableLiveData<MoviesResponse>()
    val movies : LiveData<MoviesResponse>
    get() = _movies

    fun getMovies() {
        job = Coroutines.ioThenMain(
            { repository.getMovies() },
            { _movies.value = it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}