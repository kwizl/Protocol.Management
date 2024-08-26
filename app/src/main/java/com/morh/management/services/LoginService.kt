package com.morh.management.services

import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import com.morh.management.ApiClient
import com.morh.management.models.UserDTO
import com.morh.management.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService {

    // Calls API For Authenticating. Returns a Bearer Token when successful
    public fun getToken(user: User): String? {
        val call = ApiClient.apiService.getToken(user)
        var token: String? = null

        try
        {
            val response = call.execute();
            val res = response.body();
            token = res?.Token
        }
        catch (ex: Exception)
        {
            Log.i(TAG, "Failure: ${ex.message}")
        }

        return token
    }
}