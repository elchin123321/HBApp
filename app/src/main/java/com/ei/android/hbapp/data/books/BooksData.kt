package com.ei.android.hbapp.data.books

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.books.BooksDomain

sealed class BooksData:Abstract.Object<BooksDomain, BooksDataToDomainMapper> {
    data class Success(private val books: List<BookData>): BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)


    }
    data class Fail(private val e: Exception): BooksData(){
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(e)


    }


}