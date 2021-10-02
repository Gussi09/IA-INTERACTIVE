package com.gacd.iainteractive.ui.auth

import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gacd.iainteractive.R
import com.gacd.iainteractive.data.network.Resource
import com.gacd.iainteractive.data.repositories.AuthRepository
import com.gacd.iainteractive.data.responses.LoginResponse
import com.gacd.iainteractive.ui.auth.LoginFormState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    //Function Login Connect ViewModel With Repository Auth Model
    fun login(
        username: String?,
        password: String?
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(username!!,password!!)
    }

    suspend fun saveAuthToken(token: String,tokenType: String) {
        repository.saveAuthToken(token,tokenType)
    }

    //DataChanged Validations From State Form
    fun loginDataChanged(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()){
          _loginForm.value = LoginFormState(emptyValues = R.string.required_login)
        } else if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}