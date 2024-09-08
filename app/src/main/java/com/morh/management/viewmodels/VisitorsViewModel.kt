package com.morh.management.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.morh.management.features.LocalDatabase
import com.morh.management.models.Visitor
import com.morh.management.repository.SundayDateRepository
import com.morh.management.repository.TokenRepository
import com.morh.management.services.VisitorsService
import com.morh.management.tables.SundayDate
import com.morh.management.wrappers.PaginationRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.R)
class VisitorsViewModel(application: Application): AndroidViewModel(application) {

    private val _visitorsService = VisitorsService()
    private var _tokenRepository: TokenRepository
    private var _sundayDateRepository: SundayDateRepository

    init {
        val tokenDao = LocalDatabase.getInstance(application).getTokenDao()
        val sundayDateDao = LocalDatabase.getInstance(application).getSundayDateDao()

        _tokenRepository = TokenRepository(tokenDao)
        _sundayDateRepository = SundayDateRepository(sundayDateDao)
    }

    // Gets All Visitors
    private suspend fun getAllVisitors(): List<Visitor>?
    {
        val request = PaginationRequest()
        val token = _tokenRepository.getToken().last()

        val visitors = _visitorsService.GetAll(token.TokenVal, request)
        return visitors
    }

    // Makes Async to Sync
    fun GetAll(): List<Visitor>?
    {
        var visitors: List<Visitor>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            visitors = getAllVisitors()
        }
        runBlocking {
            job.join()
        }

        return visitors
    }

    // Get All Members
    @SuppressLint("SimpleDateFormat")
    private suspend fun getMissingVisitors(datePicked: String?): List<Visitor>?
    {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        val sdf = SimpleDateFormat("MM/dd/yyyy")
        var currentDate = sdf.format(java.util.Date())

        if (day == Calendar.SUNDAY && datePicked == null) {
            val dt = SundayDate(0, currentDate)
            _sundayDateRepository.insert(dt)
        }
        else if (day == Calendar.SUNDAY) {
            val date = _sundayDateRepository.getDate().last()

            if (date.AttendanceDate == null) {
                val dt = SundayDate(0, datePicked!!)
                _sundayDateRepository.insert(dt)
                currentDate = datePicked
            }
            else {
                currentDate = date.AttendanceDate
            }

        }
        else {
            currentDate = _sundayDateRepository.getDate().last().AttendanceDate
        }

        val token = _tokenRepository.getToken().last()

        val visitors =  _visitorsService.GetMissing(token.TokenVal, 1, 2000, currentDate)
        return visitors
    }

    // Makes Async to Sync
    fun GetMissing(datePicked: String?): List<Visitor>?
    {
        var visitors: List<Visitor>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            visitors = getMissingVisitors(datePicked)
        }
        runBlocking {
            job.join()
        }

        return visitors
    }

    // Get Visitors Attendance
    @SuppressLint("SimpleDateFormat")
    private suspend fun getVisitorsAttendance(datePicked: String?): List<Visitor>?
    {
        val count = _sundayDateRepository.getDate()
        if (count.size > 1) {
            val dt = count.last()
            _sundayDateRepository.delete(dt.id)
        }

        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        val sdf = SimpleDateFormat("MM/dd/yyyy")
        var currentDate = sdf.format(java.util.Date())

        if (day == Calendar.SUNDAY && datePicked == null) {
            val dt = SundayDate(0, currentDate)
            _sundayDateRepository.insert(dt)
        }
        else if (day == Calendar.SUNDAY) {
            val date = _sundayDateRepository.getDate().last()

            if (date.AttendanceDate == null) {
                val dt = SundayDate(0, datePicked!!)
                _sundayDateRepository.insert(dt)
                currentDate = datePicked
            }
            else {
                currentDate = datePicked
            }

        }
        else {
            currentDate = _sundayDateRepository.getDate().last().AttendanceDate
        }

        val token = _tokenRepository.getToken().last()

        val visitors =  _visitorsService.GetAttended(token.TokenVal, 1, 2000, currentDate)
        return visitors
    }

    // Makes Async to Sync
    fun GetAttendance(datePicked: String?): List<Visitor>?
    {
        var visitors: List<Visitor>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            visitors = getVisitorsAttendance(datePicked)
        }
        runBlocking {
            job.join()
        }

        return visitors
    }
}