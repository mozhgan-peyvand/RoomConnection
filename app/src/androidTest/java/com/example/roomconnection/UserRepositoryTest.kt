package com.example.roomconnection

import com.example.roomconnection.data.user.User
import com.example.roomconnection.data.user.UserRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRepositoryTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetUsers() = runBlockingTest {
        val users = listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie")
        )
        val fakeUserLocalDataSource = FakeUserUserLocalDataSource()
        fakeUserLocalDataSource.saveUser(users)
        val userRepository = UserRepository(fakeUserLocalDataSource)

        val result = userRepository.getUsers().first()

        assertThat(result).containsExactlyElementsIn(users)
    }
}