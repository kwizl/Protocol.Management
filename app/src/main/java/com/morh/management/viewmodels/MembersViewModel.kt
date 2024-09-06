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
    private suspend fun getMembers(): List<Member>?
    {
        val request = PaginationRequest()
        val token = repository.getToken().last()

        val members =  _membersService.GetMembers(token.TokenVal, request)
        return members
    }

    // Makes Async to Sync
    fun AllMembers(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getMembers()
        }
        runBlocking {
            job.join()
        }

        return members
    }
}