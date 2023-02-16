package com.example.roomconnection.data.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(private val userLocalDataSource: UserLocalDataSource) {

    suspend fun insert(users: List<User>) {
        withContext(Dispatchers.IO){
            userLocalDataSource.saveUser(users)
        }
    }
    fun getUsers(): Flow<List<User>> {
        return userLocalDataSource.getUsers()
    }
}