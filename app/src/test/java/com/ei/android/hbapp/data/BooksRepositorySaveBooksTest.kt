package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.data.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.cache.BooksCacheMapper
import com.ei.android.hbapp.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksRepositorySaveBooksTest :BaseBooksRepositoryTest(){

    @Test
    fun test_save_books() = runBlocking{
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(
            listOf(
                BookData(0,"name0","ot"),
                BookData(1,"name1","ot")
            )
        )

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                BookData(0,"name0 db","ot"),
                BookData(1,"name1 db","ot")
            )
        )

        assertEquals(expectedCache, actualCache)

    }


    private inner class TestBooksCloudDataSource: BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0,"name0","ot"),
                BookCloud(1,"name1","ot")
            )

        }

    }

    private inner class TestBooksCacheDataSource: BooksCacheDataSource {

        private val list = ArrayList<BookDB>()
        override fun fetchBooks(): List<BookDB> {
            return list
        }

        override fun saveBooks(books: List<BookData>) {
            books.map{book->
                list.add(BookDB().apply {
                    //this = book.mapTo()
                })

            }
        }
    }



}