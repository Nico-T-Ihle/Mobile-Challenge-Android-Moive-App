package com.example.mobilechallange2023.Data.repository

import com.example.mobilechallange2023.Data.dao.UserDao
import com.example.mobilechallange2023.Data.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}

