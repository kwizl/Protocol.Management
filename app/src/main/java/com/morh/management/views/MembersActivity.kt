package com.morh.management.views

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.features.MembersCustomAdapter
import com.morh.management.models.Member
import com.morh.management.viewmodels.MembersViewModel
import com.morh.management.viewmodels.VisitorsViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityLoginBinding
import com.morh.protocolmanagement.databinding.ActivityMembersBinding


class MembersActivity : AppCompatActivity() {

    private lateinit var _searchView: SearchView
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _customAdapter: MembersCustomAdapter

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_members)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]
        val members = _membersViewModel.AllMembers()

        _searchView = findViewById<SearchView>(R.id.MemberSearchView)!!

        _recyclerView = findViewById<RecyclerView>(R.id.MemberRecyclerView)!!
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
    private fun filter(newText: String, members: List<Member>?)
    {
        val filteredMembers = ArrayList<Member>()

        if (members != null) {
            for (member in members)
            {
                if (member.Name.lowercase().contains(newText.lowercase()))
                {
                    filteredMembers.add(member)
                }
            }
        }

        _customAdapter.filteredList(filteredMembers)
        _customAdapter.notifyDataSetChanged()
    }
}