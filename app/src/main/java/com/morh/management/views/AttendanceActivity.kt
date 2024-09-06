package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.MembersViewModel
import com.morh.management.viewmodels.VisitorsViewModel
import com.morh.protocolmanagement.R

class AttendanceActivity : AppCompatActivity() {
    private lateinit var _memberCardView: CardView
    private lateinit var _visitorCardView: CardView
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _visitorsViewModel: VisitorsViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        _memberCardView = findViewById<CardView>(R.id.MemberCardView)
        _visitorCardView = findViewById<CardView>(R.id.VisitorCardView)


        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]
        val allMembers = _membersViewModel.GetAll()
        val missingMembers = _membersViewModel.GetMissing()
        val membersAttendance = _membersViewModel.GetAttendance()

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]
        val allVisitors = _visitorsViewModel.GetAll()
        val missingVisitors = _visitorsViewModel.GetMissing()
        val visitorsAttendance = _visitorsViewModel.GetAttendance()

        _memberCardView.setOnClickListener {
            startActivity(Intent(this, MembersActivity::class.java))
        }

        _visitorCardView.setOnClickListener {
            startActivity(Intent(this, VisitorsActivity::class.java))
        }
    }
}