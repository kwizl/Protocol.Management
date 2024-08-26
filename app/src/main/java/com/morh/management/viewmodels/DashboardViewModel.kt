package com.morh.management.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.morh.management.features.DataStoreManager
import com.morh.management.services.MembersService
import com.morh.management.services.VisitorsService

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val _membersService = MembersService()
    private val _visitorsService = VisitorsService()
    private val dataStore = DataStoreManager(application)

    // Returns number of Members
    suspend fun membersCount(): Int? {
        val token = dataStore.GetTokenLocally("access_token")
        val count = _membersService.GetMembers(token)

        return count?.count()
    }

    // Returns number of Visitors
    suspend fun visitorsCount(): Int? {
        val token = dataStore.GetTokenLocally("access_token")
        val count = _visitorsService.GetVisitors(token)

        return count?.count()
    }
}