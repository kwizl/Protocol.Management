package com.morh.management.services

import android.content.Context
import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import com.morh.management.ApiClient
import com.morh.management.models.BearerToken
import com.morh.management.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService {

    // Calls API For Authenticating. Returns a Bearer Token when successful
    public fun getToken(user: User): String? {
        val call = ApiClient.apiService.getToken(user)
        var token: String? = null

        call.enqueue(object : Callback<BearerToken> {
            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<BearerToken>, response: Response<BearerToken>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        token = it.Token
                        Log.i(TAG, "Response: Success")
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onFailure(call: Call<BearerToken>, t: Throwable) {
                Log.i(TAG, "Failure: ${t.message}")
            }
        })

        if (token != null) {

        }

        return token
    }
}