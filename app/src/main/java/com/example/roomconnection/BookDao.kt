package com.example.roomconnection

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book)

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Int): Book?

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Delete
    fun delete(book: Book)
}