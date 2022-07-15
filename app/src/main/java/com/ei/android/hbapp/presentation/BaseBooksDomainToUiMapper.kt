package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.domain.BookDomain
import com.ei.android.hbapp.domain.BookDomainToUiMapper
import com.ei.android.hbapp.domain.BooksDomainToUiMapper
import com.ei.android.hbapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Success(books,bookMapper)
    override fun map(errorType: ErrorType) = BooksUi.Fail(errorType, resourceProvider)
}