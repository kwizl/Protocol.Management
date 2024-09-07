package com.morh.management.interfaces

import com.morh.management.models.Member
import com.morh.management.models.UserDTO
import com.morh.management.models.User
import com.morh.management.models.Visitor
import com.morh.management.wrappers.PaginationRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("Login")
    fun getToken(@Body user: User): Call<UserDTO>

    @POST("api/v1/Members/List")
    fun getAllMembers(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Member>?>

    @POST("api/v1/Visitors/List")
    fun getAllVisitors(@Header("Authorization") auth: String, @Body request: PaginationRequest): Call<List<Visitor>?>

    @GET("api/v1/Members/Missed/Service")
    fun getMembersMissing(@Header("Authorization") auth: String, @Query("Page") Page: Int, @Query("PageSize") PageSize: Int, @Query(value = "Date", encoded = true) Date: String): Call<List<Member>?>

    @GET("api/v1/Members/Attended/Service")
    fun getMembersAttended(@Header("Authorization") auth: String, @Query("Page") Page: Int, @Query("PageSize") PageSize: Int, @Query(value = "Date", encoded = true) Date: String): Call<List<Member>?>

    @GET("api/v1/Visitors/Missed/Service")
    fun getVisitorsMissing(@Header("Authorization") auth: String, @Query("Page") Page: Int, @Query("PageSize") PageSize: Int, @Query(value = "Date", encoded = true) Date: String): Call<List<Visitor>?>

    @GET("api/v1/Visitors/Attended/Service")
    fun getVisitorsAttended(@Header("Authorization") auth: String, @Query("Page") Page: Int, @Query("PageSize") PageSize: Int, @Query(value = "Date", encoded = true) Date: String): Call<List<Visitor>?>
}