package com.morh.management.features

import android.app.Application
import android.content.Context
import android.os.Build
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.morh.management.interfaces.TokenDao
import com.morh.management.tables.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

@Database(entities = [Token::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun getTokenDao(): TokenDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        @RequiresApi(Build.VERSION_CODES.R)
        fun getInstance(context: Context): LocalDatabase {
            // If Instance is null, then return it
            // If it is then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, LocalDatabase::class.java, "local.db")
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}