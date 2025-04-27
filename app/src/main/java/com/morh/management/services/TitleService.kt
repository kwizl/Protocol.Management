package com.morh.management.services

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.morh.management.features.ApiClient
import com.morh.management.models.Member
import com.morh.management.models.Title
import com.morh.management.wrappers.PagedResponse
import com.morh.management.wrappers.PaginationRequest


class TitleService {

    // Call the List endpoint of the API
    public fun GetTitles(token: String, request: PaginationRequest): List<Title>?
    {
        val value = "Bearer ${token}"
        val call = ApiClient.apiService.getAllTitles(value, request)
        var titles: List<Title>? = null

        val response = call.execute();
        val res = response.body();
        if (res != null) {
            titles = res
        }

        return titles
    }
}