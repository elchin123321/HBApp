package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.net.BookCloud
import com.ei.android.hbapp.data.net.BookService

interface BooksCloudDataSource {
    suspend fun fetchBooks():List<BookCloud>

    class Base(private val service: BookService): BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }
}