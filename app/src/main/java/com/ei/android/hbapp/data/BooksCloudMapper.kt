package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract

interface BooksCloudMapper:Abstract.Mapper {
    fun map(cloudList:List<Abstract.Object<BookData, ToBookMapper>>):List<BookData>

    class Base(private val bookMapper:ToBookMapper):BooksCloudMapper{
        override fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>) = cloudList.map{bookCloud ->
                bookCloud.map(bookMapper)

        }

    }
}