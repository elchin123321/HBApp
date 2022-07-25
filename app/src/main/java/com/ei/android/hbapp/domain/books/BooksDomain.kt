package com.ei.android.hbapp.domain.books

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.ErrorType
import com.ei.android.hbapp.presentation.books.BooksUi

/**
 * to rename to BooksDomain by lead
 */
sealed class BooksDomain: Abstract.Object<BooksUi, BooksDomainToUiMapper> {
    class Success(private val books:List<BookDomain>): BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)

    }

    class Fail(private val errorType: ErrorType): BooksDomain(){
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(errorType)


    }
}