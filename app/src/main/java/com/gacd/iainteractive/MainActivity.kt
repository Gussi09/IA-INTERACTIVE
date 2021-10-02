package com.gacd.iainteractive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.gacd.iainteractive.data.UserPreferences
import com.gacd.iainteractive.ui.auth.AuthActivity
import com.gacd.iainteractive.ui.home.HomeActivity
import com.gacd.iainteractive.utils.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        // Validation with Preferences to Load or Not AuthActivity

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        }
        )
    }
}