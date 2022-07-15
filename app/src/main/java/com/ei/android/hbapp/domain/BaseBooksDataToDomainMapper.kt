package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.BookDataToDomainMapper
import com.ei.android.hbapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper(private val mapper: BookDataToDomainMapper):BooksDataToDomainMapper {
    override fun map(books: List<BookData>) = BooksDomain.Success(books,mapper)


    override fun map(e: Exception) = BooksDomain.Fail(e)

}