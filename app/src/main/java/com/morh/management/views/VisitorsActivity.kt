package com.morh.management.views

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.features.MembersCustomAdapter
import com.morh.management.features.VisitorsCustomAdapter
import com.morh.management.models.Member
import com.morh.management.models.Visitor
import com.morh.management.viewmodels.MembersViewModel
import com.morh.management.viewmodels.VisitorsViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityMembersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class VisitorsActivity : ComponentActivity() {
    private lateinit var _binding: ActivityMembersBinding
    private lateinit var _visitorsViewModel: VisitorsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // MVVM Pattern Binds View to ViewModel
        _binding = ActivityMembersBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_visitors)

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]

        val visitors = _visitorsViewModel.AllVisitors()

        try {
            // get the reference of RecyclerView
            val recyclerView: RecyclerView = findViewById(R.id.VisitorRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)

            val customAdapter = VisitorsCustomAdapter(visitors)
            recyclerView.adapter = customAdapter
        }
        catch (ex: Exception)
        {
            Log.i(TAG, "${ex.message}")
        }
    }
}