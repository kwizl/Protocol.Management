package com.morh.management.tables

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Titles")
data class Titles(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "Code") val Code: String,
    @ColumnInfo(name = "Name") val Name: String,
    @ColumnInfo(name = "DateCreated") val DateCreated: String,
    @ColumnInfo(name = "DateModified") val DateModified: String
)