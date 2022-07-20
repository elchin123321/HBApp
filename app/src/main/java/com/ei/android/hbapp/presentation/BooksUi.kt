package com.ei.android.hbapp.presentation


import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.R
import com.ei.android.hbapp.domain.BookDomain
import com.ei.android.hbapp.domain.BookDomainToUiMapper
import com.ei.android.hbapp.domain.ErrorType



sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {
    abstract fun cache(uiDataCache: UiDataCache): Unit
    data class Base(private val books: List<BookUi>) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)

        override fun cache(uiDataCache: UiDataCache){
            uiDataCache.cache(books)
        }

    }
}