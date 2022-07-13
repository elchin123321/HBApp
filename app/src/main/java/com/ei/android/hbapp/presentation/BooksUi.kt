package com.ei.android.hbapp.presentation


import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.domain.ErrorType



sealed class BooksUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    class Success(
        private val communication: BooksCommunication,
        private val books: List<Book>
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) = communication.show(books)
    }

    class Fail(
        private val communication: BooksCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider //ErrorTypeHandler
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when (errorType) { //todo move to other class
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unaviable
                else -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getString(messageId))
        }

    }
}