package com.morh.management.models

import com.google.type.DateTime

data class Visitor(
    val Code: String,
    val Name: String,
    val Contact: String,
    val Gender: String,
    val ResidenceCode: String?,
    val Remarks: String?,
    val DateAttendance: String,
    var DateCreated: String,
    val DateModified: String?,
)
