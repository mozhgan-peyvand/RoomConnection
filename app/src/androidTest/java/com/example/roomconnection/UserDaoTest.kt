package com.example.roomconnection

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.roomconnection.data.user.User
import com.example.roomconnection.data.user.UserDao
import com.example.roomconnection.data.user.UserDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDaoTest {

    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDao
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        // Initialize the in-memory database and DAO
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = database.userDao()
    }

    @After
    fun cleanup() {
        // Close the database after each test
        database.close()
    }

    @Test
    fun testGetUsers() = runBlockingTest {
        // Insert some users into the database
        val users = listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie")
        )
        userDao.insertUsers(users)
        // Get the list of users as a Flow
        val flow = userDao.getUsers()

        // Collect the Flow and assert that it emits the expected list of users
        testScope.launch {
            flow.collect { userList ->
                assertThat(userList).containsExactlyElementsIn(users)
            }
        }
    }
}
