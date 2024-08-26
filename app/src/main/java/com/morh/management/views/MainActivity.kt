package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.collection.emptyLongSet
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.morh.management.viewmodels.LoginViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _loginViewModel: LoginViewModel

    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // MVVM Pattern Binds View to ViewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _loginViewModel = ViewModelProvider(this)[LoginViewModel::class]
        binding.LoginButton.setOnClickListener {
            val username = binding.Username.text.toString()
            val password = binding.Password.text.toString()

            var state: Boolean = false

            val job = CoroutineScope(Dispatchers.Default).launch {
                state = _loginViewModel.loginUser(username, password)
            }
            runBlocking {
                job.join()
            }

            try {
                if (state) {
                    val intent = Intent(this, MembersActivity::class.java)
                    startActivity(intent)
                }
                else {
                    finish();
                    this.startActivity(getIntent());
                }
            }
            catch (ex: Exception)
            {
                Log.i(TAG, "Failure: ${ex.message}")
            }
        }
    }
}
