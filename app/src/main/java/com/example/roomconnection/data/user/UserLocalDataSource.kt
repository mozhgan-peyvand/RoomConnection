package com.example.roomconnection.data.user

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
     fun getUsers(): Flow<List<User>>
    suspend fun saveUser(user: List<User>)
}