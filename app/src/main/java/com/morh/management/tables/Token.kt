package com.morh.management.tables

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Token")
data class Token(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "TokenVal") val TokenVal: String
)