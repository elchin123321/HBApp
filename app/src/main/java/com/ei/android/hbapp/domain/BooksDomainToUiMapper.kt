package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.presentation.BooksUI

interface BooksDomainToUiMapper:Abstract.Mapper {
    fun map(books:List<Book>):BooksUI
    fun map(errorType:ErrorType):BooksUI
}