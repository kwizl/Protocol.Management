package com.morh.management.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.morh.management.features.LocalDatabase
import com.morh.management.repository.TokenRepository
import com.morh.management.services.MembersService
import com.morh.management.services.VisitorsService
import com.morh.management.tables.Token
import com.morh.management.wrappers.PaginationRequest
import kotlinx.coroutines.Dispatchers

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private var token: String?
    private val _membersService = MembersService()
    private val _visitorsService = VisitorsService()
    private val _request = PaginationRequest()
    private var repository: TokenRepository

    init {
        val dao = LocalDatabase.getInstance(application).getTokenDao()
        repository = TokenRepository(dao)
        token = repository.getToken().last().TokenVal
    }

    // Returns number of Members
    suspend fun membersCount(): Int? {
        val count = token?.let { _membersService.GetMembers(it, _request) }

        return count?.count()
    }

    // Returns number of Visitors
    suspend fun visitorsCount(): Int? {
        val count = token?.let { _visitorsService.GetVisitors(it, _request) }

        return count?.count()
    }
}