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
import com.morh.management.features.VisitorsCustomAdapter
import com.morh.management.models.Visitor
import com.morh.management.viewmodels.VisitorsViewModel
import com.morh.protocolmanagement.R

class VisitorsAttendanceActivity : AppCompatActivity() {

    private lateinit var _recyclerView: RecyclerView
    private lateinit var _customAdapter: VisitorsCustomAdapter
    private lateinit var _visitorsViewModel: VisitorsViewModel

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_visitors_attendance)

        val date = intent.getStringExtra("VisitorDate")

        _visitorsViewModel = ViewModelProvider(this)[VisitorsViewModel::class]
        val visitors = _visitorsViewModel.GetAttendance(date)

        _recyclerView = findViewById<RecyclerView>(R.id.VisitorAttendanceRecyclerView)!!
        _recyclerView.layoutManager = LinearLayoutManager(this)
        _recyclerView.setHasFixedSize(true)

        _customAdapter = VisitorsCustomAdapter(visitors)
        _recyclerView.adapter = _customAdapter
    }
}