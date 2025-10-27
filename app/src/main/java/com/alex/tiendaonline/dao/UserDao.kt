package com.alex.tiendaonline.dao

import androidx.room.*
import com.alex.tiendaonline.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<User>
}