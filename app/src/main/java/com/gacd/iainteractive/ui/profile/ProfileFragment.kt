package com.gacd.iainteractive.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.gacd.iainteractive.R
import com.gacd.iainteractive.data.network.Resource
import com.gacd.iainteractive.data.network.profile.UserApi
import com.gacd.iainteractive.data.repositories.UserRepository
import com.gacd.iainteractive.data.responses.User
import com.gacd.iainteractive.databinding.FragmentProfileBinding
import com.gacd.iainteractive.ui.base.BaseFragment
import com.gacd.iainteractive.utils.visible
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ProfileFragment : BaseFragment<ProfileViewModel,FragmentProfileBinding,UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    binding.progressBar.visible(false)

                    updateUI(it.value)
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)

                }
            }
        })
        //LogOut Button Click
        binding.logout.setOnClickListener{
            logout()
        }
    }
    // Function to Share User Info in the UI
    private fun updateUI(user: User) {
        tv_name.text = (user.first_name + " " + user.last_name)
        tv_email.text = (getString(R.string.prompt_email) + ": " + user.email)
        tv_cardnumber.text = (getString(R.string.card_number) + ": " + user.card_number)
    }

    override fun getViewModel() = ProfileViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): UserRepository {
        //Send token and tokenType to UserApi has parameters
        val token = runBlocking { userPreferences.authToken.first() }
        val tokenType = runBlocking { userPreferences.tokenType.first() }

        val api = remoteDataSource.buildApi(UserApi::class.java,token,tokenType)

        return UserRepository(api)
    }
}