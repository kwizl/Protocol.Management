package com.morh.management.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.morh.management.tables.Token

@Dao
interface TokenDao {
    @Insert
    fun insert(vararg token: Token)

    @Update
    fun update(vararg token: Token)

    @Query("DELETE FROM Token")
    fun deleteAll()

    @Query("SELECT * FROM Token ORDER BY id ASC")
    fun get(): List<Token>
}