package com.morh.management.repository

import com.morh.management.interfaces.TitleDao
import com.morh.management.models.Title

class TitleRepository(private val titleDao: TitleDao) {

    suspend fun insert(title: Title) {
        return titleDao.insert(title)
    }

    suspend fun delete(id: Int) {
        return titleDao.delete(id)
    }

    suspend fun truncate() {
        return titleDao.deleteAll()
    }

    fun getTitles(): List<Title>
    {
        return titleDao.get();
    }
}