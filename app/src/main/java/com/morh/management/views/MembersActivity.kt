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
        val members = _membersViewModel.GetCurrentAll()
        val titles = _membersViewModel.GetAllTitles()
        val titleDict = mutableMapOf<String, String>()

        if (titles != null) {
            for (title in titles) {
                titleDict[title.Code] = title.Name
            }
        }

        if (members != null) {
            for (member in members)
            {
                // Mutates Member name if the member in a not a Priest or Elder
                if (member.TitleCode == "TIT_7HA6" || member.TitleCode.isNullOrEmpty())
                {
                    continue
                }
                member.Name = "${titleDict[member.TitleCode]} ${member.Name}"
            }
        }

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

    // Function for filtering members by searching
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