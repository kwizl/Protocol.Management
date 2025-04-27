package com.morh.management.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Titles")
data class Title(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "Code") val Code: String,
    @ColumnInfo(name = "Name") val Name: String,
    @ColumnInfo(name = "DateCreated") val DateCreated: String,
    @ColumnInfo(name = "DateModified") val DateModified: String?
)
