package com.gacd.iainteractive.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gacd.iainteractive.data.network.Resource
import com.gacd.iainteractive.data.repositories.UserRepository
import com.gacd.iainteractive.data.responses.User
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _user : MutableLiveData<Resource<User>> = MutableLiveData()
    val user: LiveData<Resource<User>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}