package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.data.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.cache.BooksCacheMapper
import com.ei.android.hbapp.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class BooksRepositoryTest:BaseBooksRepositoryTest(){
    // TODO: make test
    private val unknownHostException = UnknownHostException()
    @Test
    fun test_no_connection_no_cache() = runBlocking{
        val testCloudDataSource = TestBooksCloudDataSource(
            returnSuccess = false
        )
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
            )

        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(unknownHostException)

        assertEquals(expected,actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking{
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(0,"name0","ot"),
                BookData(1,"name1","ot"),
                BookData(2,"name2","ot")
            )
        )
        assertEquals(expected,actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking{
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(10,"name10","ot"),
                BookData(11,"name11","ot"),
                BookData(12,"name12","ot")
            )
        )
        assertEquals(expected,actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(10,"name10","ot"),
                BookData(11,"name11","ot"),
                BookData(12,"name12","ot")
            )
        )
        assertEquals(expected,actual)
    }


    private inner class TestBooksCacheDataSource (
        private val returnSuccess: Boolean
    ): BooksCacheDataSource {

        override fun fetchBooks(): List<BookDB> {
            return if(returnSuccess){
                return listOf(
                    BookDB().apply {
                        id = 10
                        name = "name10"
                    },
                    BookDB().apply {
                        id = 11
                        name = "name11"
                    },
                    BookDB().apply {
                        id = 12
                        name = "name12"
                    }

                )
            }else{
                 emptyList()
            }
        }

        override fun saveBooks(books: List<BookData>) {
            //todo
        }

    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess:Boolean,
        private val errorTypeNoConnection:Boolean=true): BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            if(returnSuccess){
                return listOf(
                    BookCloud(0,"name0","ot"),
                    BookCloud(1,"name1","ot"),
                    BookCloud(2,"name2","ot")
                )
            }else{
                if(errorTypeNoConnection) {
                    throw unknownHostException
                }else{
                    throw IOException()
                }
            }
        }

    }


}