package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.presentation.BooksUi

interface BooksDomainToUiMapper:Abstract.Mapper {
    fun map(books:List<BookDomain>):BooksUi
    fun map(errorType:ErrorType):BooksUi
}