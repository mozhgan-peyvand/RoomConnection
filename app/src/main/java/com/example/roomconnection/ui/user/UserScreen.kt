package com.example.roomconnection.ui.user

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.example.roomconnection.data.user.UserRepository

@Composable
fun UserListScreen(userRepository: UserRepository) {
    var users = userRepository.getUsers().collectAsState(initial = emptyList())



    Scaffold(
        topBar = { TopAppBar(title = { Text("User List") }) }
    ) {
        LazyColumn {

            items(users.value) { user ->
                Text(text = user.name.toString())
            }
        }
    }
}