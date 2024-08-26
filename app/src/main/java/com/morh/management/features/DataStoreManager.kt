package com.morh.management.features

import android.content.Context
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val store = context.dataStore

    suspend fun SetTokenLocally(token: String) {
        val access_token = stringPreferencesKey("access_token")
        store.edit { settings ->
            settings[access_token] = token
        }
    }

    suspend fun GetTokenLocally(key: String): String
    {
        val keyStore = stringPreferencesKey(key)
        val valueFlow: Flow<String> = store.data
            .map { preferences ->
                // No type safety.
                preferences[keyStore].toString()
            }

        Log.i(TAG, "Token: ${valueFlow.toString()}")
        return valueFlow.toString()
    }
}