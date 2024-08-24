package com.morh.management.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.LoginViewModel
import com.morh.protocolmangement.R
import com.morh.protocolmangement.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _loginViewModel = ViewModelProvider(this).get(LoginViewModel::class)
        binding.LoginButton.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()

            _loginViewModel.loginUser(username, password)
        }
    }
}
