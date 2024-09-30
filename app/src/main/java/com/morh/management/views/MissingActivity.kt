package com.morh.management.views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.morh.management.viewmodels.MembersViewModel
import com.morh.management.viewmodels.VisitorsViewModel
import com.morh.protocolmanagement.R
import java.text.SimpleDateFormat
import java.util.Locale

class MissingActivity : AppCompatActivity() {
    private lateinit var _memberCardView: CardView
    private lateinit var _visitorCardView: CardView
    private lateinit var _totalMembersTextView: TextView
    private lateinit var _membersMissedTextView: TextView
    private lateinit var _totalVisitorsTextView: TextView
    private lateinit var _visitorsMissedTextView: TextView
    private lateinit var _missingMembersTextView: TextView
    private lateinit var _missingVisitorsTextView: TextView
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _visitorsViewModel: VisitorsViewModel

    private val calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UnsafeIntentLaunch", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missing)

        _memberCardView = findViewById<CardView>(R.id.MemberCardView)
        _visitorCardView = findViewById<CardView>(R.id.VisitorCardView)
        _totalMembersTextView = findViewById<TextView>(R.id.TotalMembersText)
        _membersMissedTextView = findViewById<TextView>(R.id.MembersMissedText)
        _totalVisitorsTextView = findViewById<TextView>(R.id.TotalVisitorsText)
        _visitorsMissedTextView = findViewById<TextView>(R.id.VisitorsMissedText)
        _missingMembersTextView = findViewById<TextView>(R.id.MissingMembersText)
        _missingVisitorsTextView = findViewById<TextView>(R.id.MissingVisitorsText)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]

        val allMembers = _membersViewModel.GetAll()
        val membersMissing = _membersViewModel.GetMissing(null)

        if (allMembers != null) {
            _totalMembersTextView.text = "Total Members: ${allMembers.size.toString()}"
        }
        else {
            _totalMembersTextView.text = "0"
        }

        if (membersMissing != null) {
            _membersMissedTextView.text = "Attendance: ${membersMissing.size.toString()}"
        }
        else {
            _membersMissedTextView.text = "0"
        }

        if (allMembers != null && membersMissing != null) {
            var num = allMembers.size - membersMissing.size
            _missingMembersTextView.text = "Missing: ${num.toString()}"
        }
        else {
            _missingMembersTextView.text = "0"
        }

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]

        val allVisitors = _visitorsViewModel.GetAll()
        val visitorsMissing = _visitorsViewModel.GetAttendance(null)

        if (allVisitors != null) {
            _totalVisitorsTextView.text = "Total Visitors: ${allVisitors.size.toString()}"
        }
        else {
            _totalVisitorsTextView.text = "0"
        }

        if (allVisitors != null && visitorsMissing != null) {
            val num = allVisitors.size - visitorsMissing.size
            _missingVisitorsTextView.text = "Missing: ${num.toString()}"
        }
        else {
            _missingVisitorsTextView.text = "0"
        }

        if (visitorsMissing != null) {
            _visitorsMissedTextView.text = "Attendance: ${visitorsMissing.size.toString()}"
        }
        else {
            _visitorsMissedTextView.text = "0"
        }

        _memberCardView.setOnClickListener {
            showMemberDatePicker()
        }

        _visitorCardView.setOnClickListener {
            showVisitorDatePicker()
        }
    }

    // DatePicker for Member Attendance
    private fun showMemberDatePicker() {
        var date: String = ""
        val datePickerDialog = DatePickerDialog(this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)

            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
            date = dateFormat.format(selectedDate.time)

            val intent = Intent(this, MembersAttendanceActivity::class.java).also {
                it.putExtra("MemberDate", date)
                startActivity(it)
            }
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    // DatePicker for Visitor Attendance
    private fun showVisitorDatePicker() {
        var date: String = ""
        val datePickerDialog = DatePickerDialog(this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)

            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
            date = dateFormat.format(selectedDate.time)

            val intent = Intent(this, VisitorsMissingActivity::class.java).also {
                it.putExtra("VisitorDate", date)
                startActivity(it)
            }
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
}