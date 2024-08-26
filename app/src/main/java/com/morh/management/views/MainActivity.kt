package com.morh.management.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.LoginViewModel
import com.morh.protocolmanagement.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _loginViewModel = ViewModelProvider(this)[LoginViewModel::class]
        binding.LoginButton.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()

            CoroutineScope(Dispatchers.Default).launch {
                val state = _loginViewModel.loginUser(username, password)

                if (state) {
                    val intent =
                        Intent().setClassName("com.morh.management.views", "MembersActivity")
                    startActivity(intent)
                }
            }
        }
    }
}
