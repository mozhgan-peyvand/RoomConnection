package com.example.roomconnection.ui.book

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.example.roomconnection.data.book.Book
import com.example.roomconnection.data.book.BookRepository

@Composable
fun BookScreen(bookRepository: BookRepository) {
    var books = remember { mutableStateOf(emptyList<Book>()) }

    LaunchedEffect(Unit) {
        books.value = bookRepository.getAllBooks()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Book List") }) }
    ) {
        LazyColumn {
            items(books.value) { book ->
                Text(text = book.summary)
            }
        }
    }
}