package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.BooksDomain
import java.lang.Exception

interface BooksDataToDomainMapper:Abstract.Mapper {

    fun map(books: List<BookData>):BooksDomain
    fun map(e: Exception):BooksDomain
}