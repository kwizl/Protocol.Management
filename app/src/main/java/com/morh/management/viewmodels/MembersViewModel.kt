package com.morh.management.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.morh.management.features.LocalDatabase
import com.morh.management.interfaces.SundayDateDao
import com.morh.management.models.Member
import com.morh.management.repository.SundayDateRepository
import com.morh.management.repository.TokenRepository
import com.morh.management.services.MembersService
import com.morh.management.tables.SundayDate
import com.morh.management.wrappers.PaginationRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.R)
class MembersViewModel(application: Application) : AndroidViewModel(application) {

    private var _tokenRepository: TokenRepository
    private val _membersService = MembersService();
    private var _sundayDateRepository: SundayDateRepository

    init {
        val tokenDao = LocalDatabase.getInstance(application).getTokenDao()
        val sundayDateDao = LocalDatabase.getInstance(application).getSundayDateDao()

        _tokenRepository = TokenRepository(tokenDao)
        _sundayDateRepository = SundayDateRepository(sundayDateDao)
    }

    // Get All Members
    private suspend fun getAllMembers(): List<Member>?
    {
        val request = PaginationRequest()
        val token = _tokenRepository.getToken().last()

        val members =  _membersService.GetAll(token.TokenVal, request)
        return members
    }

    // Makes Async to Sync
    fun GetAll(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getAllMembers()
        }
        runBlocking {
            job.join()
        }

        return members
    }

    // Get Missing Members
    @SuppressLint("SimpleDateFormat")
    private suspend fun getMissingMembers(datePicked: String?): List<Member>?
    {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        var currentDate = sdf.format(java.util.Date())

        if (day == Calendar.SUNDAY && datePicked == null) {
            _sundayDateRepository.truncate()

            val dt = SundayDate(0, currentDate)
            _sundayDateRepository.insert(dt)
        }
        else if (day == Calendar.SUNDAY) {
            val date = _sundayDateRepository.getDate().last()
            currentDate = date.AttendanceDate
        }
        else {
            val date = _sundayDateRepository.getDate().last()
            currentDate = "09/01/2024"
        }

        val token = _tokenRepository.getToken().last()

        val members =  _membersService.GetMissing(token.TokenVal, 1, 2000, currentDate)
        return members
    }

    // Makes Async to Sync
    fun GetMissing(datePicked: String?): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getMissingMembers(datePicked)
        }
        runBlocking {
            job.join()
        }

        return members
    }

    // Get Members Attendance
    @SuppressLint("SimpleDateFormat")
    private suspend fun getMembersAttendance(datePicked: String?): List<Member>?
    {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        var currentDate = sdf.format(java.util.Date())

        if (day == Calendar.SUNDAY && datePicked == null) {
            _sundayDateRepository.truncate()

            val dt = SundayDate(0, currentDate)
            _sundayDateRepository.insert(dt)
        }
        else if (day == Calendar.SUNDAY) {
            val date = _sundayDateRepository.getDate().last()
            currentDate = date.AttendanceDate
        }
        else {
            val dt = SundayDate(0, "09/01/2024")
            _sundayDateRepository.insert(dt)
            currentDate = _sundayDateRepository.getDate().last().AttendanceDate
        }

        val token = _tokenRepository.getToken().last()

        val members =  _membersService.GetAttendance(token.TokenVal, 1, 2000, currentDate)
        return members
    }

    // Makes Async to Sync
    fun GetAttendance(datePicked: String?): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getMembersAttendance(datePicked)
        }
        runBlocking {
            job.join()
        }

        return members
    }
}