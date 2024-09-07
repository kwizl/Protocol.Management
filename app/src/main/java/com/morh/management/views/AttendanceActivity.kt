package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
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
    private lateinit var _totalMembersTextView: TextView
    private lateinit var _membersAttendedTextView: TextView
    private lateinit var _totalVisitorsTextView: TextView
    private lateinit var _visitorsAttendedTextView: TextView
    private lateinit var _missingMembersTextView: TextView
    private lateinit var _missingVisitorsTextView: TextView
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _visitorsViewModel: VisitorsViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UnsafeIntentLaunch", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        _memberCardView = findViewById<CardView>(R.id.MemberCardView)
        _visitorCardView = findViewById<CardView>(R.id.VisitorCardView)
        _totalMembersTextView = findViewById<TextView>(R.id.TotalMembersText)
        _membersAttendedTextView = findViewById<TextView>(R.id.MembersAttendedText)
        _totalVisitorsTextView = findViewById<TextView>(R.id.TotalVisitorsText)
        _visitorsAttendedTextView = findViewById<TextView>(R.id.VisitorsAttendedText)
        _missingMembersTextView = findViewById<TextView>(R.id.MissingMembersText)
        _missingVisitorsTextView = findViewById<TextView>(R.id.MissingVisitorsText)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]

        val allMembers = _membersViewModel.GetAll()
        val membersAttendance = _membersViewModel.GetAttendance(null)

        if (allMembers != null) {
            _totalMembersTextView.text = "Total Members: ${allMembers.size.toString()}"
        }
        else {
            _totalMembersTextView.text = "0"
        }

        if (membersAttendance != null) {
            _membersAttendedTextView.text = "Attendance: ${membersAttendance.size.toString()}"
        }
        else {
            _membersAttendedTextView.text = "0"
        }

        if (allMembers != null && membersAttendance != null) {
            var num = allMembers.size - membersAttendance.size
            _missingMembersTextView.text = "Missing: ${num.toString()}"
        }
        else {
            _missingMembersTextView.text = "0"
        }

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]

        val allVisitors = _visitorsViewModel.GetAll()
        val visitorsAttendance = _visitorsViewModel.GetAttendance(null)

        if (allVisitors != null) {
            _totalVisitorsTextView.text = "Total Visitors: ${allVisitors.size.toString()}"
        }
        else {
            _totalVisitorsTextView.text = "0"
        }

        if (allVisitors != null && visitorsAttendance != null) {
            val num = allVisitors.size - visitorsAttendance.size
            _missingVisitorsTextView.text = "Missing: ${num.toString()}"
        }
        else {
            _missingVisitorsTextView.text = "0"
        }

        if (visitorsAttendance != null) {
            _visitorsAttendedTextView.text = "Attendance: ${visitorsAttendance.size.toString()}"
        }
        else {
            _visitorsAttendedTextView.text = "0"
        }

        _memberCardView.setOnClickListener {
            startActivity(Intent(this, MembersActivity::class.java))
        }

        _visitorCardView.setOnClickListener {
            startActivity(Intent(this, VisitorsAttendanceActivity::class.java))
        }
    }
}