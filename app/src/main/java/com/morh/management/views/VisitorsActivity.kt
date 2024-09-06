package com.morh.management.views

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import com.morh.protocolmanagement.databinding.ActivityVisitorsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class VisitorsActivity : AppCompatActivity() {
    private lateinit var _searchView: SearchView
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _visitorsViewModel: VisitorsViewModel
    private lateinit var _customAdapter: VisitorsCustomAdapter

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_visitors)

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]
        val visitors = _visitorsViewModel.GetAll()

        _searchView = findViewById<SearchView>(R.id.VisitorSearchView)!!

        _recyclerView = findViewById<RecyclerView>(R.id.VisitorRecyclerView)!!
        _recyclerView.layoutManager = LinearLayoutManager(this)
        _recyclerView.setHasFixedSize(true)

        _customAdapter = VisitorsCustomAdapter(visitors)
        _recyclerView.adapter = _customAdapter

        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText, visitors)
                return true
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filter(newText: String, visitors: List<Visitor>?)
    {
        val filteredVisitors = ArrayList<Visitor>()

        if (visitors != null) {
            for (member in visitors)
            {
                if (member.Name.lowercase().contains(newText.lowercase()))
                {
                    filteredVisitors.add(member)
                }
            }
        }

        _customAdapter.filteredList(filteredVisitors)
        _customAdapter.notifyDataSetChanged()
    }
}