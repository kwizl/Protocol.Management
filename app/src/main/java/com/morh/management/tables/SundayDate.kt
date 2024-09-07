package com.morh.management.tables

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "SundayDate")
data class SundayDate(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "AttendanceDate") val AttendanceDate: String
)