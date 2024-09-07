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
    public fun GetAll(token: String, request: PaginationRequest): List<Visitor>? {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getAllVisitors(value, request)
        var visitors: List<Visitor>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            visitors = res
        }

        return visitors
    }

    public fun GetAttended(token: String, Page: Int, PageNumber: Int, Date: String): List<Visitor>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getVisitorsAttended(value, Page, PageNumber, Date)
        var visitors: List<Visitor>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            visitors = res
        }

        return visitors
    }

    public fun GetMissing(token: String, Page: Int, PageNumber: Int, Date: String): List<Visitor>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getVisitorsMissing(value, Page, PageNumber, Date)
        var visitors: List<Visitor>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            visitors = res
        }

        return visitors
    }
}