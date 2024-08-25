package com.morh.management

import com.morh.management.models.BearerToken
import com.morh.management.models.Member
import com.morh.management.models.User
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PagedResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Accept: application/json")
    @POST("Login")
    fun getToken(user: User): Call<BearerToken>

    @Headers("Accept: application/json")
    @POST("api/v1/Members")
    fun getMembers(@Header("Authorization") auth: String): Call<PagedResponse<Member>>

    @Headers("Accept: application/json")
    @POST("api/v1/Visitors")
    fun getVisitors(@Header("Authorization") auth: String): Call<PagedResponse<Visitor>>
}