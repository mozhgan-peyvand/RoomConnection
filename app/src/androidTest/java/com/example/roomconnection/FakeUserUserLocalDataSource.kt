package com.example.roomconnection

import com.example.roomconnection.data.user.UserLocalDataSource
import com.example.roomconnection.data.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserUserLocalDataSource : UserLocalDataSource {
    private val users = mutableListOf<User>()

    override  fun getUsers(): Flow<List<User>> {
        return flow {
            emit(users)
        }
    }

    override suspend fun saveUser(users: List<User>) {
        this.users.addAll(users)
    }
}
