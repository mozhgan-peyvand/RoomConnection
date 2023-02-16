package com.example.roomconnection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomconnection.data.book.BookRepository
import com.example.roomconnection.data.user.User
import com.example.roomconnection.data.user.UserDatabase
import com.example.roomconnection.data.user.UserUserLocalDataSourceImpl
import com.example.roomconnection.data.user.UserRepository
import com.example.roomconnection.theme.RoomConnectionTheme
import com.example.roomconnection.ui.user.UserListScreen

class MainActivity : ComponentActivity() {
    private lateinit var userRepository: UserRepository
    private lateinit var bookRepository: BookRepository
    private lateinit var userLocalDataSourceImpl: UserUserLocalDataSourceImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bookRepository = BookRepository(BookDatabase.getInstance(this).bookDao())
        userLocalDataSourceImpl = UserUserLocalDataSourceImpl(UserDatabase.getInstance(this).userDao())
        userRepository = UserRepository(userLocalDataSourceImpl)
        setContent {
            LaunchedEffect(Unit) {
                userRepository.insert(
                    listOf(
                        User(
                            id = 1,
                            name = "mozhgan"
                        )
                    )
                )
            }

//            LaunchedEffect(key1 = Unit){
//                bookRepository.insert(
//                    Book(
//                        id = 2,
//                        author = "mozhgan",
//                        summary = "hi im books",
//                        title = "life book"
//                    )
//                )
//            }
            UserListScreen(userRepository = userRepository)
//            BookScreen(bookRepository = bookRepository)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomConnectionTheme {
        Greeting("Android")
    }
}