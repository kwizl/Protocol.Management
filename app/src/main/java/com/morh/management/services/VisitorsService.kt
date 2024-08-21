package com.morh.management.services

import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import com.morh.management.ApiClient
import com.morh.management.models.Visitor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitorsService {
    public fun GetVisitors()
    {
        val call = ApiClient.apiService.getVisitors()

        call.enqueue(object : Callback<List<Visitor>> {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<List<Visitor>>, response: Response<List<Visitor>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.i(TAG, "Response: ${it}")
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onFailure(call: Call<List<Visitor>>, t: Throwable) {
                Log.i(TAG, "Failure: ${t.message}")
            }
        })
    }
}