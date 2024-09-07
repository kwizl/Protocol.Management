package com.morh.management.repository

import androidx.lifecycle.LiveData
import com.morh.management.interfaces.TokenDao
import com.morh.management.tables.Token

class TokenRepository(private val tokenDao: TokenDao) {

    fun getToken(): List<Token>
    {
        return tokenDao.get();
    }
    suspend fun update(token: Token) {
        return tokenDao.insert(token)
    }

    suspend fun insert(token: Token) {
        return tokenDao.insert(token)
    }

    suspend fun truncate() {
        return tokenDao.deleteAll()
    }
}