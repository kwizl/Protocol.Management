package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.models.Member
import com.morh.management.services.MembersService

class MembersViewModel : ViewModel() {

    private val _membersService = MembersService();

    // Get All Members
    fun getMembers(): List<Member>?
    {
        val token: String = ""

        val members = _membersService.GetMembers(token)
        return members
    }
}