package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.cache.BooksCacheMapper
import kotlinx.coroutines.delay
import java.lang.Exception
import java.net.UnknownHostException

interface BooksRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val booksCloudMapper: BooksCloudMapper,
        private val booksCacheMapper: BooksCacheMapper
    ) : BooksRepository{
        override suspend fun fetchBooks() = try {
            val booksCacheList = cacheDataSource.fetchBooks()
            if(booksCacheList.isEmpty()) {
                val booksCloudList = cloudDataSource.fetchBooks()
                val books = booksCloudMapper.map(booksCloudList)
                cacheDataSource.saveBooks(books)
                BooksData.Success(books)
            }else{
                BooksData.Success(booksCacheMapper.map(booksCacheList))
            }

            }catch (e: Exception){
                BooksData.Fail(e)
            }



    }
}