package com.morh.management.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var binding: ActivityMembersBinding
    private lateinit var _visitorsViewModel: VisitorsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // MVVM Pattern Binds View to ViewModel
        binding = ActivityMembersBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_visitors)

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]

        var visitors : List<Visitor>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            visitors = _visitorsViewModel.getVisitors()
        }
        runBlocking {
            job.join()
        }
    }
}