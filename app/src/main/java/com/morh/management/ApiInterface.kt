package com.morh.management

import com.morh.management.models.UserDTO
import com.morh.management.models.Member
import com.morh.management.models.User
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PagedResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("Login")
    fun getToken(@Body user: User): Call<UserDTO>

    @POST("api/v1/Members")
    fun getMembers(@Header("Authorization") auth: String): Call<PagedResponse<Member>>

    @POST("api/v1/Visitors")
    fun getVisitors(@Header("Authorization") auth: String): Call<PagedResponse<Visitor>>
}