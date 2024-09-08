package com.morh.management.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.morh.management.tables.SundayDate
import com.morh.management.tables.Token

@Dao
interface SundayDateDao {
    @Insert
    fun insert(vararg sundayDate: SundayDate)

    @Query("DELETE FROM SundayDate WHERE id = :key")
    fun delete(key: Int)

    @Query("DELETE FROM SundayDate")
    fun deleteAll()

    @Query("SELECT * FROM SundayDate ORDER BY id ASC")
    fun get(): List<SundayDate>
}