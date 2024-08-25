package com.morh.management.services

import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import com.morh.management.ApiClient
import com.morh.management.models.Member
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PagedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitorsService {
    public fun GetVisitors(token: String): List<Visitor>?
    {
        val call = ApiClient.apiService.getVisitors(token)
        var visitors: List<Visitor>? = null

        call.enqueue(object : Callback<PagedResponse<Visitor>> {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<PagedResponse<Visitor>>, response: Response<PagedResponse<Visitor>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.i(TAG, "Response: Success")
                        visitors = it.Data
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onFailure(call: Call<PagedResponse<Visitor>>, t: Throwable) {
                Log.i(TAG, "Failure: ${t.message}")
            }
        })

        return visitors
    }
}