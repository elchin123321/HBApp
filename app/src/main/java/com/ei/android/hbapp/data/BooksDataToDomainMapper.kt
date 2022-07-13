package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.data.net.BookCloud
import com.ei.android.hbapp.domain.BookDomain
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

interface BooksDataToDomainMapper:Abstract.Mapper {

    fun map(books: List<Book>):BookDomain
    fun map(e: Exception):BookDomain
}