package com.ei.android.hbapp.data.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book

interface BookCacheMapper: Abstract.Mapper {
    fun map(bookdb:BookDB):Book

    class Base:BookCacheMapper{
        override fun map(bookdb: BookDB) = Book(bookdb.id, bookdb.name)

    }
}
