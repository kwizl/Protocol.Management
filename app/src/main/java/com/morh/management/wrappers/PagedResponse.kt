package com.morh.management.wrappers

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.lang.reflect.Array

@Serializable
data class PagedResponse<T> (

    @SerialName("PagedNumber")
    val PagedNumber: Int,

    @SerialName("PageSize")
    val PageSize: Int,

    @SerialName("FirstPage")
    val FirstPage: String,

    @SerialName("LastPage")
    val LastPage: String,

    @SerialName("TotalPages")
    val TotalPages: Int,

    @SerialName("TotalRecords")
    val TotalRecords: Int,

    @SerialName("NextPage")
    val NextPage: String,

    @SerialName("PreviousPage")
    val PreviousPage: String? = null,

    @SerialName("Data")
    val Data: List<T>?,

    @SerialName("Succeeded")
    val Succeeded: Boolean,

    @SerialName("Errors")
    val Errors: String? = null,

    @SerialName("Message")
    val Message: String? = null,

    @SerialName("Code")
    val Code: String? = null
)
