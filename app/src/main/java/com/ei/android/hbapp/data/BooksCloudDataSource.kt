package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.net.BookCloud
import com.ei.android.hbapp.data.net.BooksService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BooksCloudDataSource {
    suspend fun fetchBooks():List<BookCloud>

    class Base(private val service: BooksService): BooksCloudDataSource {
        private val gson = Gson()
        private val type = object :TypeToken<List<BookCloud>>(){}.type
        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(service.fetchBooks().string(), type)

    }
}
