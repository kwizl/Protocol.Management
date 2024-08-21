package com.morh.management

import com.morh.management.models.BearerToken
import com.morh.management.models.Member
import com.morh.management.models.User
import com.morh.management.models.Visitor
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Accept: application/json")
    @POST("Login")
    fun getToken(user: User): Call<BearerToken>

    @Headers("Accept: application/json")
    @POST("api/v1/Members")
    fun getMembers(): Call<List<Member>>

    @Headers("Accept: application/json")
    @POST("api/v1/Visitors")
    fun getVisitors(): Call<List<Visitor>>
}