package com.morh.management.views

import android.os.Build
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.features.MembersCustomAdapter
import com.morh.management.viewmodels.MembersViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityMembersBinding


class MembersActivity : ComponentActivity() {
    private lateinit var _binding: ActivityMembersBinding
    private lateinit var _membersViewModel: MembersViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // MVVM Pattern Binds View to ViewModel
        _binding = ActivityMembersBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_members)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]

        val members = _membersViewModel.AllMembers()

        try {
            // get the reference of RecyclerView
            val recyclerView: RecyclerView = findViewById(R.id.MemberRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)

            val customAdapter = MembersCustomAdapter(members)
            recyclerView.adapter = customAdapter
        }
        catch (ex: Exception)
        {
            Log.i(TAG, "${ex.message}")
        }
    }
}