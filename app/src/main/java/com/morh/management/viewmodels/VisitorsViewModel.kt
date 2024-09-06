package com.morh.management.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.morh.management.features.LocalDatabase
import com.morh.management.models.Visitor
import com.morh.management.repository.TokenRepository
import com.morh.management.services.MembersService
import com.morh.management.services.VisitorsService
import com.morh.management.tables.Token
import com.morh.management.wrappers.PaginationRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.R)
class VisitorsViewModel(application: Application): AndroidViewModel(application) {

    private val _visitorsService = VisitorsService();
    private var repository: TokenRepository

    init {
        val dao = LocalDatabase.getInstance(application).getTokenDao()
        repository = TokenRepository(dao)
    }

    // Gets All Visitors
    private suspend fun getVisitors(): List<Visitor>?
    {
        val request = PaginationRequest()
        val token = repository.getToken().last()

        val visitors = _visitorsService.GetVisitors(token.TokenVal, request)
        return visitors
    }

    // Makes Async to Sync
    fun AllVisitors(): List<Visitor>?
    {
        var visitors: List<Visitor>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            visitors = getVisitors()
        }
        runBlocking {
            job.join()
        }

        return visitors
    }
}