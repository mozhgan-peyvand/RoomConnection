package com.example.roomconnection.data.user

import kotlinx.coroutines.flow.Flow

class UserUserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {
    override  fun getUsers(): Flow<List<User>> {
        return userDao.getUsers()
    }

    override suspend fun saveUser(user: List<User>) {
        userDao.insertUsers(user)
    }
}