package com.ei.android.hbapp.data.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.ToBookMapper

interface BooksCacheMapper:Abstract.Mapper {
    fun map(books:List<Abstract.Object<BookData,ToBookMapper>>):List<BookData>

    class Base(private val mapper: ToBookMapper):BooksCacheMapper{
        override fun map(books: List<Abstract.Object<BookData,ToBookMapper>>) = books.map{bookDb->
            bookDb.map(mapper)

        }
    }
}