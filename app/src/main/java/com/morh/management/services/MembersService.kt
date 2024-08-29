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
    public fun GetMembers(token: String, request: PaginationRequest): List<Member>?
    {
        try
        {
            val value = "Bearer ${token}"
            val call = ApiClient.apiService.getMembers(value, request)
            var members: List<Member>? = null

            val response = call.execute();
            val res = response.body();
            if (res != null) {
                members = res
            }

            return members
        }
        catch (ex: Exception)
        {
            Log.i(TAG, "Failure: ${ex.message}")
        }
        return TODO("Provide the return value")
    }
}