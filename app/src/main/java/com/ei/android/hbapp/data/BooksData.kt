package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.domain.BookDomain

sealed class BooksData:Abstract.Object<BookDomain, BooksDataToDomainMapper>() {
    data class Success(private val books: List<Book>):BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)


    }
    data class Fail(private val e: Exception):BooksData(){
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(e)
        }

    }


}