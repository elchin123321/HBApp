package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.R
import com.ei.android.hbapp.domain.BookDomain
import com.ei.android.hbapp.domain.BookDomainToUiMapper
import com.ei.android.hbapp.domain.BooksDomainToUiMapper
import com.ei.android.hbapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Base(books.map{
            it.map(bookMapper)
        })

    override fun map(errorType: ErrorType):BooksUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unaviable
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Base(listOf(BookUi.Fail(message)))
    }
}