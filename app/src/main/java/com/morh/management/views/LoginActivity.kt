package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.LoginViewModel
import com.morh.protocolmanagement.databinding.ActivityLoginBinding


class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var _loginViewModel: LoginViewModel

    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sleeps for 3 seconds
        Thread.sleep(10000)
        installSplashScreen()

        // MVVM Pattern Binds View to ViewModel
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _loginViewModel = ViewModelProvider(this)[LoginViewModel::class]

        binding.LoginButton.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()

            val state = _loginViewModel.Login(username, password)

            if (state) {
                val intent = Intent(this, MembersActivity::class.java)
                startActivity(intent)
            }
            else {
                finish();
                this.startActivity(getIntent());
            }
        }
    }
}
