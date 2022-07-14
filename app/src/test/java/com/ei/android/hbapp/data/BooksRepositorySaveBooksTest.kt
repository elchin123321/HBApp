package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.data.cache.BookCacheMapper
import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.data.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.cache.BooksCacheMapper
import com.ei.android.hbapp.data.net.BookCloud
import com.ei.android.hbapp.data.net.BookCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

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
                Book(0,"name0"),
                Book(1,"name1")
            )
        )

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                Book(0,"name0 db"),
                Book(1,"name1 db")
            )
        )

        assertEquals(expectedCache, actualCache)

    }


    private inner class TestBooksCloudDataSource: BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0,"name0"),
                BookCloud(1,"name1")
            )

        }

    }

    private inner class TestBooksCacheDataSource: BooksCacheDataSource {

        private val list = ArrayList<BookDB>()
        override fun fetchBooks(): List<BookDB> {
            return list
        }

        override fun saveBooks(books: List<Book>) {
            books.map{book->
                list.add(BookDB().apply {
                    this.id = book.id
                    this.name = "${book.name} db"
                })

            }
        }
    }



}