package com.example.mobilechallange2023.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobilechallange2023.Data.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>
}
