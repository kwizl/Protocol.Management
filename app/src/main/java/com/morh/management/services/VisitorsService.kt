package com.morh.management.services

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.google.gson.Gson
import com.morh.management.features.ApiClient
import com.morh.management.models.Member
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PagedResponse

class VisitorsService {
    public fun GetVisitors(token: String): List<Visitor>? {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getVisitors(value)
        var visitors: List<Visitor>? = null

        try
        {
            val response = call.execute();
            val res = response.body();
            if (res != null) {
                visitors = res.Data.values as List<Visitor>?
            }
        }
        catch (ex: Exception)
        {
            Log.i(TAG, "Failure: ${ex.message}")
        }

        return visitors
    }
}