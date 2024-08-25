package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.models.Visitor
import com.morh.management.services.VisitorsService

class VisitorsViewModel: ViewModel() {

    private val _visitorsService = VisitorsService();

    // Gets All Visitors
    fun getVisitors(): List<Visitor>?
    {
        val token: String = ""

        val visitors = _visitorsService.GetVisitors(token)
        return visitors
    }
}