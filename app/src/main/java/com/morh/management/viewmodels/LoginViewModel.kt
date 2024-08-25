package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.models.User
import com.morh.management.services.LoginService

class LoginViewModel : ViewModel() {
    private val _loginService = LoginService();

    // Returns true if authentication is successful otherwise false
    fun loginUser(username: String, password: String): Boolean
    {
        val user = User(username, password)
        val login = _loginService.getToken(user)

        return login != null
    }
}