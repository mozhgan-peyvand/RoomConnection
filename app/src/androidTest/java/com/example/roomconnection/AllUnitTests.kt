package com.example.roomconnection

import com.example.roomconnection.data.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Suite::class)
@Suite.SuiteClasses(
    BookRepositoryTest::class,
    UserDaoTest::class,
    UserRepositoryTest::class
)
class AllUnitTests