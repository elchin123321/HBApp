package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.net.BookCloud
import com.ei.android.hbapp.data.net.BooksService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BooksCloudDataSource {
    suspend fun fetchBooks():List<BookCloud>

    class Base(private val service: BooksService,
    private val gson:Gson): BooksCloudDataSource {
        private val type = object :TypeToken<List<BookCloud>>(){}.type // todo make a wrapper
        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(service.fetchBooks().string(), type)

    }
}
