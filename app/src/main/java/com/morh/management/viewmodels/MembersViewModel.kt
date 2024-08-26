package com.morh.management.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.morh.management.features.DataStoreManager
import com.morh.management.models.Member
import com.morh.management.services.MembersService

class MembersViewModel(application: Application) : AndroidViewModel(application) {

    private val _membersService = MembersService();
    private val dataStore = DataStoreManager(application)

    // Get All Members
    suspend fun getMembers(): List<Member>?
    {
        val token = dataStore.GetTokenLocally("access_token")

        val members = _membersService.GetMembers(token)
        return members
    }
}