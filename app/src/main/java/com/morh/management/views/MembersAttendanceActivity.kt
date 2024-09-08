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

class MembersAttendanceActivity : AppCompatActivity() {

    private lateinit var _searchView: SearchView
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _customAdapter: MembersCustomAdapter
    private lateinit var _membersViewModel: MembersViewModel

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_members_attendance)

        val date = intent.getStringExtra("MemberDate")

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]
        val members = _membersViewModel.GetAttendance(date)

        _searchView = findViewById<SearchView>(R.id.MemberAttendanceSearchView)!!

        _recyclerView = findViewById<RecyclerView>(R.id.MembersAttendanceRecyclerView)!!
        _recyclerView.layoutManager = LinearLayoutManager(this)
        _recyclerView.setHasFixedSize(true)

        _customAdapter = MembersCustomAdapter(members)
        _recyclerView.adapter = _customAdapter

        _searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText, members)
                return true
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filter(newText: String, members: List<Member>?) {
        val filteredMembers = ArrayList<Member>()

        if (members != null) {
            for (member in members) {
                if (member.Name.lowercase().contains(newText.lowercase())) {
                    filteredMembers.add(member)
                }
            }
        }

        _customAdapter.filteredList(filteredMembers)
        _customAdapter.notifyDataSetChanged()
    }
}