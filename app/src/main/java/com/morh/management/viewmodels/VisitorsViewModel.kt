package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.models.Visitor
import com.morh.management.services.VisitorsService

class VisitorsViewModel: ViewModel() {

    private val _visitorsService = VisitorsService();

    fun getVisitors(): List<Visitor>?
    {
        val visitors = _visitorsService.GetVisitors()
        return visitors
    }
}