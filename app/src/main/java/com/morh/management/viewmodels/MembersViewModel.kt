package com.morh.management.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.morh.management.features.LocalDatabase
import com.morh.management.models.Member
import com.morh.management.repository.TokenRepository
import com.morh.management.services.MembersService
import com.morh.management.tables.Token
import com.morh.management.wrappers.PaginationRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.R)
class MembersViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: TokenRepository
    private val _membersService = MembersService();

    init {
        val dao = LocalDatabase.getInstance(application).getTokenDao()
        repository = TokenRepository(dao)
    }

    // Get All Members
    private suspend fun getAllMembers(): List<Member>?
    {
        val request = PaginationRequest()
        val token = repository.getToken().last()

        val members =  _membersService.GetAll(token.TokenVal, request)
        return members
    }

    // Makes Async to Sync
    fun GetAll(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getAllMembers()
        }
        runBlocking {
            job.join()
        }

        return members
    }

    // Get Missing Members
    private suspend fun getMissingMembers(): List<Member>?
    {
        val request = PaginationRequest()
        val token = repository.getToken().last()

        val members =  _membersService.GetMissing(token.TokenVal, request)
        return members
    }

    // Makes Async to Sync
    fun GetMissing(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getMissingMembers()
        }
        runBlocking {
            job.join()
        }

        return members
    }

    // Get Members Attendance
    private suspend fun getMembersAttendance(): List<Member>?
    {
        val request = PaginationRequest()
        val token = repository.getToken().last()

        val members =  _membersService.GetMissing(token.TokenVal, request)
        return members
    }

    // Makes Async to Sync
    fun GetAttendance(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getMembersAttendance()
        }
        runBlocking {
            job.join()
        }

        return members
    }
}