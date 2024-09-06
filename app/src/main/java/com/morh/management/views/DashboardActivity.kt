package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.LoginViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityLoginBinding


class DashboardActivity : ComponentActivity() {

    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}
