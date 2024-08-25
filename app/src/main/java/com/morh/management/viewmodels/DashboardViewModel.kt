package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.services.MembersService
import com.morh.management.services.VisitorsService

class DashboardViewModel : ViewModel() {
    private val _membersService = MembersService()
    private val _visitorsService = VisitorsService()

    // Returns number of Members
    fun membersCount(): Int? {
        val count = _membersService.GetMembers()
        return count?.count()
    }

    // Returns number of Visitors
    fun visitorsCount(): Int? {
        val count = _visitorsService.GetVisitors()
        return count?.count()
    }
}