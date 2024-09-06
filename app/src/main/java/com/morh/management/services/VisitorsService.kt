package com.morh.management.services

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.google.gson.Gson
import com.morh.management.features.ApiClient
import com.morh.management.models.Member
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PagedResponse
import com.morh.management.wrappers.PaginationRequest

class VisitorsService {
    public fun GetVisitors(token: String, request: PaginationRequest): List<Visitor>? {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getVisitors(value, request)
        var visitors: List<Visitor>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            visitors = res
        }

        return visitors
    }

    public fun GetAttended(token: String, request: PaginationRequest): List<Visitor>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getVisitorsAttendance(value, request)
        var visitors: List<Visitor>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            visitors = res
        }

        return visitors
    }

    public fun GetMissing(token: String, request: PaginationRequest): List<Visitor>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getVisitorsMissing(value, request)
        var visitors: List<Visitor>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            visitors = res
        }

        return visitors
    }
}