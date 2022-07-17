package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.BookDataToDomainMapper
import com.ei.android.hbapp.data.BooksDataToDomainMapper
import com.ei.android.hbapp.data.TestamentTemp
import com.ei.android.hbapp.presentation.BookUi
import com.ei.android.hbapp.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * to rename to BooksDomain by lead
 */
sealed class BooksDomain: Abstract.Object<BooksUi, BooksDomainToUiMapper> {
    class Success(private val books:List<BookDomain>):BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)

    }

    class Fail(private val errorType:ErrorType):BooksDomain(){
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(errorType)


    }
}