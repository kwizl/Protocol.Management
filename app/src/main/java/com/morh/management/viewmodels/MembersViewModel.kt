package com.morh.management.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.morh.management.features.LocalDatabase
import com.morh.management.models.Member
import com.morh.management.repository.TokenRepository
import com.morh.management.services.MembersService
import com.morh.management.tables.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MembersViewModel(application: Application) : AndroidViewModel(application) {

    private val _membersService = MembersService();
    private var repository: TokenRepository

    init {
        val dao = LocalDatabase.getInstance(application).getTokenDao()
        repository = TokenRepository(dao)
    }

    // Get All Members
    private suspend fun getMembers(): List<Member>?
    {
        val token = repository.getToken().last()

        val members =  _membersService.GetMembers(token.TokenVal)
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