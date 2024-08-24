package com.morh.management.viewmodels

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.google.api.Context
import com.morh.management.models.User
import com.morh.management.services.LoginService

class LoginViewModel : ViewModel() {
    val _loginService = LoginService();

    fun loginUser(username: String, password: String): Boolean
    {
        val user = User(username, password)
        val login = _loginService.getToken(user)

        if (login == null) {
            return false
        }
        else {
            return true
        }
    }
}