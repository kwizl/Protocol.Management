package com.morh.management.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.morh.management.viewmodels.MembersViewModel
import com.morh.management.viewmodels.VisitorsViewModel

class DashboardService {

    private lateinit var _membersViewModel: MembersViewModel
    private lateinit var _visitorsViewModel: VisitorsViewModel

    // Counts number of Members
    @RequiresApi(Build.VERSION_CODES.R)
    fun countMembers() : Int
    {
        val count = _membersViewModel.AllMembers()
        if (count != null) {
            return count.size
        }

        return 0;
    }

    // Counts number of Visitors
    @RequiresApi(Build.VERSION_CODES.R)
    fun countVisitors() : Int
    {
        val count = _visitorsViewModel.AllVisitors()
        if (count != null) {
            return count.size
        }

        return 0;
    }
}