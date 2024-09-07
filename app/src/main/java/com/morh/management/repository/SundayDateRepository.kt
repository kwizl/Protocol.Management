package com.morh.management.repository

import androidx.lifecycle.LiveData
import com.morh.management.interfaces.SundayDateDao
import com.morh.management.interfaces.TokenDao
import com.morh.management.tables.SundayDate
import com.morh.management.tables.Token

class SundayDateRepository(private val sundayDateDao: SundayDateDao) {

    suspend fun insert(sundayDate: SundayDate) {
        return sundayDateDao.insert(sundayDate)
    }

    suspend fun truncate() {
        return sundayDateDao.deleteAll()
    }

    fun getDate(): List<SundayDate>
    {
        return sundayDateDao.get();
    }
}