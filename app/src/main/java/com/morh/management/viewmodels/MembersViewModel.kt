package com.morh.management.viewmodels

import androidx.lifecycle.ViewModel
import com.morh.management.models.Member
import com.morh.management.services.MembersService

class MembersViewModel : ViewModel() {

    private val _membersService = MembersService();

    fun getMembers(): List<Member>?
    {
        val members = _membersService.GetMembers()
        return members
    }
}