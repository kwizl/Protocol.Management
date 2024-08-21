package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.services.LoginService

class LoginViewModel : ViewModel() {
    val _loginService = LoginService();
}