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

class AttendanceActivity : AppCompatActivity() {
    private lateinit var _memberCardView: CardView
    private lateinit var _visitorCardView: CardView
    private lateinit var _totalMembersTextView: TextView
    private lateinit var _membersAttendedTextView: TextView
    private lateinit var _totalVisitorsTextView: TextView
    private lateinit var _visitorsAttendedTextView: TextView
    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _visitorsViewModel: VisitorsViewModel

    private val calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("UnsafeIntentLaunch", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        _memberCardView = findViewById<CardView>(R.id.Attendance_MemberAttendedCardView)
        _visitorCardView = findViewById<CardView>(R.id.Attendance_VisitorAttendedCardView)
        _totalMembersTextView = findViewById<TextView>(R.id.TotalMembersText)
        _membersAttendedTextView = findViewById<TextView>(R.id.MembersAttendedText)
        _totalVisitorsTextView = findViewById<TextView>(R.id.TotalVisitorsText)
        _visitorsAttendedTextView = findViewById<TextView>(R.id.VisitorsAttendedText)

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

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]

        val allVisitors = _visitorsViewModel.GetAll()
        val visitorsAttendance = _visitorsViewModel.GetAttendance(null)

        if (allVisitors != null) {
            _totalVisitorsTextView.text = "Total Visitors: ${allVisitors.size.toString()}"
        }
        else {
            _totalVisitorsTextView.text = "0"
        }

        if (visitorsAttendance != null) {
            _visitorsAttendedTextView.text = "Attendance: ${visitorsAttendance.size.toString()}"
        }
        else {
            _visitorsAttendedTextView.text = "0"
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

            val intent = Intent(this, VisitorsAttendanceActivity::class.java).also {
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