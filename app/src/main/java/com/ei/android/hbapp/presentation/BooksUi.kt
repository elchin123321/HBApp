package com.ei.android.hbapp.presentation


import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.R
import com.ei.android.hbapp.domain.BookDomain
import com.ei.android.hbapp.domain.BookDomainToUiMapper
import com.ei.android.hbapp.domain.ErrorType



sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    data class Success(
        private val books: List<BookDomain>,
        private val bookMapper:BookDomainToUiMapper
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val booksUi = books.map{
                it.map(bookMapper)
            }
            mapper.map(booksUi)
        }
    }

    data class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unaviable
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUi.Fail(message)))
        }

    }
}