package com.alex.tiendaonline.repository

import android.content.Context
import com.alex.tiendaonline.database.AppDatabase
import com.alex.tiendaonline.model.User

class UserRepository(context: Context) {
    private val userDao = AppDatabase.getDatabase(context).userDao()

    suspend fun login(email: String, password: String) = userDao.login(email, password)
    suspend fun register(user: User) = userDao.insert(user)
}