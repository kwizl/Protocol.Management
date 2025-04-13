package com.morh.management.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.morh.management.models.Title

@Dao
interface TitleDao {
    @Insert
    fun insert(vararg title: Title)

    @Query("DELETE FROM Titles WHERE id = :key")
    fun delete(key: Int)

    @Query("DELETE FROM Titles")
    fun deleteAll()

    @Query("SELECT * FROM Titles ORDER BY id ASC")
    fun get(): List<Title>
}