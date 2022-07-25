package com.ei.android.hbapp.data.books.cloud

import com.ei.android.hbapp.core.TypeTokenProvider
import com.ei.android.hbapp.data.chapters.cloud.ChapterCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    class Base(
        private val service: BooksService,
        private val gson: Gson,
    ) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(
                service.fetchBooks().string(),
                object :TypeToken<List<BookCloud>>(){}.type
            )

    }
}
