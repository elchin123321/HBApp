package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.BookDataToDomainMapper
import com.ei.android.hbapp.data.BooksDataToDomainMapper
import com.ei.android.hbapp.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * to rename to BooksDomain by lead
 */
sealed class BooksDomain: Abstract.Object<BooksUi, BooksDomainToUiMapper> {
    class Success(
        private val books:List<BookData>,
        private val bookMapper: BookDataToDomainMapper
    ):BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper):BooksUi {
            val booksDomain = books.map{
                it.map(bookMapper)
            }
            return mapper.map(booksDomain)
        }
    }

    class Fail(private val e:Exception):BooksDomain(){
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when(e){
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException ->ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )


    }
}