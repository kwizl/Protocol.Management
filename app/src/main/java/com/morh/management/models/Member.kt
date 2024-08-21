package com.morh.management.models

import com.google.type.DateTime

data class Member(
    val Code: String,
    val Name: String,
    val Contact: String?,
    val Gender: String?,
    val DepartmentCode: String?,
    val Remarks: String?,
    val DateAttendance: DateTime,
    val Transferred: Boolean? = false,
    var DateCreated: DateTime,
    val DateModified: DateTime,
    val AltarCode: String?,
    val GroupCode: String?,
    val ClusterCode: String?,
    val ResidenceCode: String?,
    val FellowshipCode: String?
)
