package com.morh.management.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.morh.management.features.DataStoreManager
import com.morh.management.models.Visitor
import com.morh.management.services.VisitorsService

class VisitorsViewModel(application: Application): AndroidViewModel(application) {

    private val _visitorsService = VisitorsService();
    private val dataStore = DataStoreManager(application)

    // Gets All Visitors
    suspend fun getVisitors(): List<Visitor>?
    {
        val token = dataStore.GetTokenLocally("access_token")

        val visitors = _visitorsService.GetVisitors(token)
        return visitors
    }
}