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

class MembersService {

    // Call the List endpoint of the API
    public fun GetMembers(token: String): List<Member>?
    {

        val call = ApiClient.apiService.getMembers(token)
        var members: List<Member>? = null

        call.enqueue(object : Callback<PagedResponse<Member>> {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<PagedResponse<Member>>, response: Response<PagedResponse<Member>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.i(TAG, "Response: Success")
                        members = it.Data
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onFailure(call: Call<PagedResponse<Member>>, t: Throwable) {
                Log.i(TAG, "Failure: ${t.message}")
            }
        })

        return members
    }
}