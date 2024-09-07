package com.morh.management.services

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.morh.management.features.ApiClient
import com.morh.management.models.Member
import com.morh.management.wrappers.PagedResponse
import com.morh.management.wrappers.PaginationRequest


class MembersService {

    // Call the List endpoint of the API
    public fun GetAll(token: String, request: PaginationRequest): List<Member>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getAllMembers(value, request)
        var members: List<Member>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            members = res
        }

        return members
    }

    public fun GetAttendance(token: String, Page: Int, PageNumber: Int, Date: String): List<Member>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getMembersAttended(value, Page, PageNumber, Date)
        var members: List<Member>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            members = res
        }

        return members
    }

    public fun GetMissing(token: String, Page: Int, PageNumber: Int, Date: String): List<Member>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getMembersMissing(value, Page, PageNumber, Date)
        var members: List<Member>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            members = res
        }

        return members
    }
}