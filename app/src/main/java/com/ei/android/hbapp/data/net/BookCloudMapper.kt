package com.ei.android.hbapp.data.net

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book

interface BookCloudMapper:Abstract.Mapper {
    fun map(id:Int, name: String): Book

    class Base:BookCloudMapper{
        override fun map(id: Int, name: String) = Book(id,name)
    }

}
