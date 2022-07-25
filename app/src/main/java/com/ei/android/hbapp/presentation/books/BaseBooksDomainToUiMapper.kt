package com.ei.android.hbapp.presentation.books

import com.ei.android.hbapp.core.ResourceProvider
import com.ei.android.hbapp.domain.books.BookDomain
import com.ei.android.hbapp.domain.books.BookDomainToUiMapper
import com.ei.android.hbapp.domain.books.BooksDomainToUiMapper
import com.ei.android.hbapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper(resourceProvider) {

    override fun map(data: List<BookDomain>) = BooksUi.Base(data.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType) =
        BooksUi.Base(listOf(BookUi.Fail(errorMessage(errorType))))
}