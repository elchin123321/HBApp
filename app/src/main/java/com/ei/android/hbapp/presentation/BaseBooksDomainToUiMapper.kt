package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.domain.BooksDomainToUiMapper
import com.ei.android.hbapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUiMapper {
    override fun map(books: List<Book>) = BooksUi.Success(communication, books)
    override fun map(errorType: ErrorType) = BooksUi.Fail(communication, errorType, resourceProvider)
}