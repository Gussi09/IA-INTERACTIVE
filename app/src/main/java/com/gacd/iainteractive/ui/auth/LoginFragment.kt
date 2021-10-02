package com.gacd.iainteractive.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.gacd.iainteractive.data.network.Resource
import com.gacd.iainteractive.data.repositories.AuthRepository
import com.gacd.iainteractive.databinding.FragmentLoginBinding
import com.gacd.iainteractive.ui.base.BaseFragment
import com.gacd.iainteractive.utils.handleApiErrors
import com.gacd.iainteractive.ui.home.HomeActivity
import com.gacd.iainteractive.utils.startNewActivity
import com.gacd.iainteractive.data.network.auth.AuthAPI
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        //LiveData Observer Validation State Form
        viewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                login.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    username.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    password.error = getString(it)
                }
            })

        //LiveData Observer Login Response
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            loading.visibility = View.GONE
            when(it){
                is Resource.Success -> {
                    //save User Preferences
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.access_token, it.value.token_type)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiErrors(it)
            }
        })

        //Listener Button Login Click
        login.setOnClickListener{
            loading.visibility = View.VISIBLE

            viewModel.login(username.text.toString().trim(),password.text.toString().trim())
        }

        //Object TextChangedListener
        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                viewModel.loginDataChanged(
                    username.text.toString().trim(),
                    password.text.toString().trim()
                )
            }
        }

        //TextChangedListener Added For Controls Form To Validate Values
        username.addTextChangedListener(afterTextChangedListener)
        password.addTextChangedListener(afterTextChangedListener)
        password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loading.visibility = View.VISIBLE
                viewModel.login(
                    username.text.toString(),
                    password.text.toString()
                )
            }
            false
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java),userPreferences)

}