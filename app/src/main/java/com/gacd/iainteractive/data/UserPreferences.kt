package com.gacd.iainteractive.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext

    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
    }

    val tokenType: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[TOKEN_TYPE]
    }

    suspend fun saveTokenType(tokenType: String){
        dataStore.edit { preferences ->
            preferences[TOKEN_TYPE] = tokenType
        }
    }
    suspend fun saveAuthToken(authToken: String){
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun clear(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object{
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val TOKEN_TYPE = preferencesKey<String>("token_key")
    }
}