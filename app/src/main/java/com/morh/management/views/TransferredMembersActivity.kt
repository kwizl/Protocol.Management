package com.morh.management.views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.unit.Constraints
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.features.MembersCustomAdapter
import com.morh.management.models.Member
import com.morh.management.viewmodels.MembersViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.R.*

class TransferredMembersActivity : AppCompatActivity() {

    private lateinit var _searchView: SearchView
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _customAdapter: MembersCustomAdapter

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(layout.activity_transferred_members)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]
        val members = _membersViewModel.GetTransferredAll()

        _searchView = findViewById<SearchView>(id.TransferredMemberSearchView)!!

        _recyclerView = findViewById<RecyclerView>(id.TransferredMemberRecyclerView)!!
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

    // Function for filtering members by searching
    @SuppressLint("NotifyDataSetChanged")
    private fun filter(newText: String, members: List<Member>?)
    {
        val filteredMembers = ArrayList<Member>()

        if (members != null) {
            for (member in members)
            {
                if (member.TitleCode == "Tit_WcPa4")
                {
                    member.Name = "Snr.Deputy Archbishop " + member.Name
                }

                if (member.TitleCode == "Tit_yWE05")
                {
                    member.Name = "Bishop " + member.Name
                }

                if (member.TitleCode == "Tit_19Bgz")
                {
                    member.Name = "Snr.Pastor " + member.Name
                }

                if (member.TitleCode == "Tit_yWE05")
                {
                    member.Name = "Bishop " + member.Name
                }

                if (member.TitleCode == "Tit_NwF3u")
                {
                    member.Name = "Snr.Overseer " + member.Name
                }

                if (member.TitleCode == "Tit_gIGjP")
                {
                    member.Name = "Overseer Elder " + member.Name
                }

                if (member.TitleCode == "Tit_Trolk")
                {
                    member.Name = "Overseer " + member.Name
                }

                if (member.TitleCode == "Tit_9EAY7")
                {
                    member.Name = "Pst. " + member.Name
                }

                if (member.TitleCode == "Tit_anAu1")
                {
                    member.Name = "Elder " + member.Name
                }

                if (member.Name.lowercase().contains(newText.lowercase()))
                {
                    filteredMembers.add(member)
                }
                else if (member.Contact?.lowercase()!!.contains(newText.lowercase()))
                {
                    filteredMembers.add(member)
                }

            }
        }

        _customAdapter.filteredList(filteredMembers)
        _customAdapter.notifyDataSetChanged()
    }
}