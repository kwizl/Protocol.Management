package com.morh.management.services

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.morh.management.features.ApiClient
import com.morh.management.models.Member
import com.morh.management.wrappers.PagedResponse


class MembersService {

    // Call the List endpoint of the API
    public fun GetMembers(token: String): List<Member>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getMembers(value)
        var members: List<Member>? = null

        try
        {
            val response = call.execute();
            val res = response.body();
            if (res != null) {
                members = res.Data.values as List<Member>?
            }
        }
        catch (ex: Exception)
        {
            Log.i(TAG, "Failure: ${ex.message}")
        }

        return members
    }
}