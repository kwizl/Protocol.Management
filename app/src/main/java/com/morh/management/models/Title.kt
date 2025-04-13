package com.morh.management.models

import com.google.type.DateTime

data class Title(
    val Code: String,
    var Name: String,
    val DateCreated: String?,
    val DateModified: String,
    val AltarCode: String?,
    val GroupCode: String?,
    val TitleCode: String?,
    val ClusterCode: String?,
    val ResidenceCode: String?,
    val FellowshipCode: String?
)
