package com.morh.management.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.morh.management.features.LocalDatabase
import com.morh.management.models.User
import com.morh.management.repository.TokenRepository
import com.morh.management.services.LoginService
import com.morh.management.tables.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.R)
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _loginService = LoginService()
    private var repository: TokenRepository
    private lateinit var token: LiveData<MutableList<Token>>

    init {
        val dao = LocalDatabase.getInstance(application).getTokenDao()
        repository = TokenRepository(dao)
    }

    // Returns true if authentication is successful otherwise false
    @RequiresApi(Build.VERSION_CODES.R)
    private suspend fun loginUser(username: String, password: String): Boolean
    {
        val user = User(username, password)
        val login = _loginService.getToken(user)

        if (login != null) {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val token = Token(0, login)

                repository.truncate()
                repository.insert(token)
            }
            runBlocking {
                job.join()
            }
            return true
        }
        else {
            return false
        }
    }

    public fun Login(username: String, password: String): Boolean
    {
        var state: Boolean = false
        val job = CoroutineScope(Dispatchers.Default).launch {
            state = loginUser(username, password)
        }
        runBlocking {
            job.join()
        }

        return state
    }
}