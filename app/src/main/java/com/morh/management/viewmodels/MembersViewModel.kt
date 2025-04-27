package com.morh.management.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.morh.management.features.LocalDatabase
import com.morh.management.models.Member
import com.morh.management.models.Title
import com.morh.management.repository.SundayDateRepository
import com.morh.management.repository.TitleRepository
import com.morh.management.repository.TokenRepository
import com.morh.management.services.MembersService
import com.morh.management.services.TitleService
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
    private var _titleRepository: TitleRepository
    private var _sundayDateRepository: SundayDateRepository

    private val _titleService = TitleService()
    private val _membersService = MembersService()

    init {
        val tokenDao = LocalDatabase.getInstance(application).getTokenDao()
        val sundayDateDao = LocalDatabase.getInstance(application).getSundayDateDao()
        val titleDao = LocalDatabase.getInstance(application).getTitleDao()

        _tokenRepository = TokenRepository(tokenDao)
        _titleRepository = TitleRepository(titleDao)
        _sundayDateRepository = SundayDateRepository(sundayDateDao)
    }

    // Get All Titles
    private fun getAllTitles(): List<Title>?
    {
        val request = PaginationRequest()
        val token = _tokenRepository.getToken().last()

        val titles = _titleService.GetTitles(token.TokenVal, request)
        return titles
    }

    // Get All Members
    private fun getAllCurrentMembers(): List<Member>?
    {
        val request = PaginationRequest()
        val token = _tokenRepository.getToken().last()

        val members =  _membersService.GetAll(token.TokenVal, request, false)
        return members
    }

    private fun getAllTransferredMembers() : List<Member>?
    {
        val request = PaginationRequest()
        val token = _tokenRepository.getToken().last()

        val members =  _membersService.GetAll(token.TokenVal, request, true)
        return members
    }

    fun GetAllTitles(): List<Title>?
    {
        var titles: List<Title>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            titles =  _titleRepository.getTitles()

            if (titles!!.isEmpty() || titles!! !=  getAllTitles())
            {
                _titleRepository.truncate()

                titles = getAllTitles()
                for (title in titles!!)
                {
                    _titleRepository.insert(title)
                }
            }
        }
        runBlocking {
            job.join()
        }

        return titles
    }

    // Makes Async to Sync
    fun GetCurrentAll(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getAllCurrentMembers()
        }
        runBlocking {
            job.join()
        }

        return members
    }

    fun GetTransferredAll(): List<Member>?
    {
        var members: List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = getAllTransferredMembers()
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
            calendar.add(Calendar.DATE, -day + 1)
            currentDate = sdf.format(calendar.time)
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
            calendar.add(Calendar.DATE, -day + 1)

            currentDate = sdf.format(calendar.time)
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

    // Returns Date
    @SuppressLint("SimpleDateFormat")
    fun GetRecentSundayDate(): String
    {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        val sdf = SimpleDateFormat("MM/dd/yyyy")
        var currentDate = sdf.format(java.util.Date())

        calendar.add(Calendar.DATE, -day + 1)

        currentDate = sdf.format(calendar.time)

        return currentDate
    }
}