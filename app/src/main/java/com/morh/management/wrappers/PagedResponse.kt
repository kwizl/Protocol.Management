package com.morh.management.wrappers

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.lang.reflect.Array

@Serializable
data class PagedResponse<T> (
    @SerialName("\$id")
    val id: String,

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
    val Data: Data<T>,

    @SerialName("Succeeded")
    val Succeeded: Boolean,

    @SerialName("Errors")
    val Errors: String? = null,

    @SerialName("Message")
    val Message: String? = null,

    @SerialName("Code")
    val Code: String? = null
)

@Serializable
data class Data<T> (
    @SerialName("'$'id")
    val id: String,

    @SerialName("\$values")
    val values: List<T>?
)
