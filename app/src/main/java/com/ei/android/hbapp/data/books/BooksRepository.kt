package com.ei.android.hbapp.data.books

import com.ei.android.hbapp.data.books.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.books.cache.BooksCacheMapper
import com.ei.android.hbapp.data.books.cloud.BooksCloudDataSource
import com.ei.android.hbapp.data.books.cloud.BooksCloudMapper
import java.lang.Exception

interface BooksRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val booksCloudMapper: BooksCloudMapper,
        private val booksCacheMapper: BooksCacheMapper
    ) : BooksRepository {

        override suspend fun fetchBooks() = try {
            val booksCacheList = cacheDataSource.read()
            if (booksCacheList.isEmpty()) {
                val booksCloudList = cloudDataSource.fetchBooks()
                val books = booksCloudMapper.map(booksCloudList)
                cacheDataSource.save(books)
                BooksData.Success(books)
            } else {
                BooksData.Success(booksCacheMapper.map(booksCacheList))
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }
    }
}