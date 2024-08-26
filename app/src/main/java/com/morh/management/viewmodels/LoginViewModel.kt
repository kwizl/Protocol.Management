package com.morh.management.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.morh.management.features.DataStoreManager
import com.morh.management.models.User
import com.morh.management.services.LoginService
import kotlinx.coroutines.awaitAll

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _loginService = LoginService()
    private val dataStore = DataStoreManager(application)

    // Returns true if authentication is successful otherwise false
    suspend fun loginUser(username: String, password: String): Boolean
    {
        val user = User(username, password)
        val login = _loginService.getToken(user)

        if (login != null) {
            dataStore.SetTokenLocally(login)
            return true
        }
        else {
            return false
        }
    }
}