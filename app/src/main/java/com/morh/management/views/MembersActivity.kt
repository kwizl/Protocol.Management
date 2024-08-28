package com.morh.management.views

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.models.Member
import com.morh.management.viewmodels.MembersViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityMembersBinding


class MembersActivity : ComponentActivity() {
    private lateinit var _binding: ActivityMembersBinding
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _recyclerView : RecyclerView

    lateinit var _memberNames: ArrayList<String>
    lateinit var _phoneNumbers: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // MVVM Pattern Binds View to ViewModel
        _binding = ActivityMembersBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_list_item)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]

        val members = _membersViewModel.AllMembers()

        // get the reference of RecyclerView
        _recyclerView = findViewById<View>(R.id.MemberRecyclerView) as RecyclerView
        _recyclerView.layoutManager = LinearLayoutManager(this)
        _recyclerView.setHasFixedSize(true)

        for (member in members!!) {
            _memberNames.add(member.Name)
            member.Contact?.let { _phoneNumbers.add(it) }
        }
    }
}