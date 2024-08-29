package com.morh.management.models

import com.google.type.DateTime

data class Member(
    val Code: String,
    val Name: String,
    val Contact: String?,
    val Gender: String?,
    val DepartmentCode: String?,
    val Remarks: String?,
    val DateAttendance: String,
    val Transferred: Boolean? = false,
    var DateCreated: String,
    val DateModified: String,
    val AltarCode: String?,
    val GroupCode: String?,
    val TitleCode: String?,
    val ClusterCode: String?,
    val ResidenceCode: String?,
    val FellowshipCode: String?
)
