package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.data.net.BookCloud
import com.ei.android.hbapp.data.net.BookCloudMapper

interface BooksCloudMapper:Abstract.Mapper {
    fun map(cloudList:List<BookCloud>):List<Book>

    class Base(private val bookMapper:BookCloudMapper):BooksCloudMapper{
        override fun map(cloudList: List<BookCloud>) = cloudList.map{bookCloud ->
                bookCloud.map(bookMapper)

        }

    }
}