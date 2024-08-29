package com.morh.management.wrappers

data class PaginationRequest(
    var Page: Int? = 1,
    var PageSize: Int? = 1000,
    var PageNumber: Int? = 1,
    var SortCriteria: String? = "ID",
    var descendingOrder: Boolean? = false,
    var Date: String? = null
)
