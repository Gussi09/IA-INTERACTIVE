package com.gacd.iainteractive.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gacd.iainteractive.data.repositories.AuthRepository
import com.gacd.iainteractive.data.repositories.BaseRepository
import com.gacd.iainteractive.data.repositories.MoviesRepository
import com.gacd.iainteractive.data.repositories.UserRepository
import com.gacd.iainteractive.ui.auth.AuthViewModel
import com.gacd.iainteractive.ui.movies.MoviesViewModel
import com.gacd.iainteractive.ui.profile.ProfileViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(repository as MoviesRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")

        }
    }
}