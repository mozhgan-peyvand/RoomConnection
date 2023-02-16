package com.example.roomconnection

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.roomconnection.data.book.Book
import com.example.roomconnection.data.book.BookDao
import com.example.roomconnection.data.book.BookDatabase
import com.example.roomconnection.data.book.BookRepository
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BookRepositoryTest {
    private lateinit var bookRepository: BookRepository
    private lateinit var bookDao: BookDao
    private lateinit var db: BookDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        bookDao = db.bookDao()
        bookRepository = BookRepository(bookDao)
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testInsertAndGetBookById() = runBlocking {
        val book = Book(1, "Title", "Author", "Summary")
        bookRepository.insert(book)

        val result = bookRepository.getBookById(1)

        assertNotNull(result)
        assertEquals(book.id, result?.id)
        assertEquals(book.title, result?.title)
        assertEquals(book.author, result?.author)
        assertEquals(book.summary, result?.summary)
    }

    @Test
    fun testGetAllBooks() = runBlocking {
        val book1 = Book(1, "Title1", "Author1", "Summary1")
        val book2 = Book(2, "Title2", "Author2", "Summary2")
        bookRepository.insert(book1)
        bookRepository.insert(book2)

        val result = bookRepository.getAllBooks()

        assertEquals(2, result.size)
        assertEquals(book1.id, result[0].id)
        assertEquals(book1.title, result[0].title)
        assertEquals(book1.author, result[0].author)
        assertEquals(book1.summary, result[0].summary)
        assertEquals(book2.id, result[1].id)
        assertEquals(book2.title, result[1].title)
        assertEquals(book2.author, result[1].author)
        assertEquals(book2.summary, result[1].summary)
    }

    @Test
    fun testDelete() = runBlocking {
        val book = Book(1, "Title", "Author", "Summary")
        bookRepository.insert(book)

        bookRepository.delete(book)

        val result = bookRepository.getBookById(1)
        assertNull(result)
    }
}
