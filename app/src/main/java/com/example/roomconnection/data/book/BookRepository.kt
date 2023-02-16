package com.example.roomconnection.data.book

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val bookDao: BookDao) {
    suspend fun insert(book: Book) {
        withContext(Dispatchers.IO){
            bookDao.insert(book)
        }
    }

    fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

   suspend fun getAllBooks(): List<Book> {
      return  withContext(Dispatchers.IO){
            bookDao.getAllBooks()
        }
    }

    fun delete(book: Book) {
        bookDao.delete(book)
    }
}