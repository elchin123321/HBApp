package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.presentation.BooksUI
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * to rename to BooksDomain by lead
 */
sealed class BookDomain: Abstract.Object<BooksUI, BooksDomainToUiMapper>() {
    class Success(private val books:List<Book>):BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e:Exception):BookDomain(){
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when(e){
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException ->ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )


    }
}