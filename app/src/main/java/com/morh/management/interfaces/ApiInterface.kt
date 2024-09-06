package com.morh.management.interfaces

import androidx.room.Query
import com.morh.management.models.Member
import com.morh.management.models.UserDTO
import com.morh.management.models.User
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PagedResponse
import com.morh.management.wrappers.PaginationRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.QueryName

interface ApiInterface {
    @POST("Login")
    fun getToken(@Body user: User): Call<UserDTO>

    @POST("api/v1/Members/List")
    fun getAllMembers(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Member>?>

    @POST("api/v1/Visitors/List")
    fun getAllVisitors(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Visitor>?>

    @POST("api/v1/Members/Missing")
    fun getMembersMissing(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Member>?>

    @POST("api/v1/Members/Attended")
    fun getMembersAttended(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Member>?>

    @POST("api/v1/Visitors/Attended")
    fun getVisitorsMissing(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Visitor>?>

    @POST("api/v1/Visitors/Attended")
    fun getVisitorsAttended(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Visitor>?>
}