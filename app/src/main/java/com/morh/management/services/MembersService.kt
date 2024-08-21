package com.morh.management.services

import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import com.morh.management.ApiClient
import com.morh.management.models.Member
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemberService {
    public fun GetMembers()
    {
        val call = ApiClient.apiService.getMembers()

        call.enqueue(object : Callback<List<Member>> {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.i(TAG, "Response: ${it}")
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                Log.i(TAG, "Failure: ${t.message}")
            }
        })
    }
}