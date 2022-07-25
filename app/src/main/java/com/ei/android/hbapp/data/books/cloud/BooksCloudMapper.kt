package com.ei.android.hbapp.data.books.cloud

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.books.BookData
import com.ei.android.hbapp.data.books.ToBookMapper

interface BooksCloudMapper:Abstract.Mapper.Data<List<BookCloud>,List<BookData>> {

    class Base(private val bookMapper: ToBookMapper): BooksCloudMapper {
        override fun map(data: List<BookCloud>) = data.map{ bookCloud ->
                bookCloud.map(bookMapper)

        }

    }
}