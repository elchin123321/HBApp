package com.ei.android.hbapp.data.books.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.books.BookData
import com.ei.android.hbapp.data.books.CommonBookData
import com.ei.android.hbapp.data.books.ToBookMapper

interface BooksCacheMapper:Abstract.Mapper.Data<List<CommonBookData>,List<BookData>> {

    class Base(private val mapper: ToBookMapper): BooksCacheMapper {
        override fun map(data: List<CommonBookData>) = data.map{ bookDb->
            bookDb.map(mapper)

        }
    }
}