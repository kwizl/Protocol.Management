package com.morh.management.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.morh.protocolmanagement.R


class DashboardActivity : AppCompatActivity() {

    private lateinit var _memberCardView: CardView
    private lateinit var _visitorCardView: CardView
    private lateinit var _missingCardView: CardView
    private lateinit var _attendanceCardView: CardView

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        _memberCardView = findViewById<CardView>(R.id.MemberCardView)
        _visitorCardView = findViewById<CardView>(R.id.VisitorCardView)
        _missingCardView = findViewById<CardView>(R.id.MissingCardView)
        _attendanceCardView = findViewById<CardView>(R.id.AttendanceCardView)

        _memberCardView.setOnClickListener {
            startActivity(Intent(this, MembersActivity::class.java))
        }

        _visitorCardView.setOnClickListener {
            startActivity(Intent(this, VisitorsActivity::class.java))
        }

        _missingCardView.setOnClickListener {
            startActivity(Intent(this, MissingActivity::class.java))
        }

        _attendanceCardView.setOnClickListener {
            startActivity(Intent(this, AttendanceActivity::class.java))
        }
    }
}
