package com.morh.management.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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

class VisitorsMissingActivity : AppCompatActivity() {
    private lateinit var _searchView: SearchView
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _customAdapter: VisitorsCustomAdapter
    private lateinit var _visitorsViewModel: VisitorsViewModel

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_visitors_missing)

        val date = intent.getStringExtra("MemberDate")

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]
        val visitors = _visitorsViewModel.GetAttendance(date)

        _recyclerView = findViewById<RecyclerView>(R.id.VisitorsMissingRecyclerView)!!
        _recyclerView.layoutManager = LinearLayoutManager(this)
        _recyclerView.setHasFixedSize(true)

        _customAdapter = VisitorsCustomAdapter(visitors)
        _recyclerView.adapter = _customAdapter

        _searchView = findViewById<SearchView>(R.id.VisitorMissingSearchView)!!
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
    private fun filter(newText: String, visitors: List<Visitor>?) {
        val filteredVisitors = ArrayList<Visitor>()

        if (visitors != null) {
            for (visitor in visitors) {
                if (visitor.Name.lowercase().contains(newText.lowercase())) {
                    filteredVisitors.add(visitor)
                }
            }
        }

        _customAdapter.filteredList(filteredVisitors)
        _customAdapter.notifyDataSetChanged()
    }
}