package com.example.roomconnection

class BookRepository(private val bookDao: BookDao) {
    fun insert(book: Book) {
        bookDao.insert(book)
    }

    fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

    fun getAllBooks(): List<Book> {
        return bookDao.getAllBooks()
    }

    fun delete(book: Book) {
        bookDao.delete(book)
    }
}