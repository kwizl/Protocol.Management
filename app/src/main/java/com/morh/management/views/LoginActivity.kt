package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.LoginViewModel
import com.morh.protocolmanagement.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var _loginViewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // MVVM Pattern Binds View to ViewModel
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _loginViewModel = ViewModelProvider(this)[LoginViewModel::class]

        binding.LoginButton.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()

            val state = _loginViewModel.Login(username, password)

            if (state) {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            else {
                val toast = Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG)
                toast.show()

                this.startActivity(getIntent())
            }
        }
    }
}
