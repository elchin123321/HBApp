package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper:BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BookDomain.Success(books)


    override fun map(e: Exception) = BookDomain.Fail(e)

}