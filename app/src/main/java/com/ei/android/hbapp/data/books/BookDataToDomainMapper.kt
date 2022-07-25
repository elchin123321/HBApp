package com.ei.android.hbapp.data.books

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.books.BookDomain

interface BookDataToDomainMapper:Abstract.Mapper {
    fun map(id: Int,name:String): BookDomain
}