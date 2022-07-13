package com.ei.android.hbapp.data.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book

interface BooksCacheMapper:Abstract.Mapper {
    fun map(books:List<BookDB>):List<Book>

    class Base(private val mapper: BookCacheMapper):BooksCacheMapper{
        override fun map(books: List<BookDB>) = books.map{bookDb->
            bookDb.map(mapper)

        }
    }
}