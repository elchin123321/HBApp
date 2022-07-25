package com.ei.android.hbapp.domain.books

import com.ei.android.hbapp.data.books.BookDataToDomainMapper

class BaseBookDataToDomainMapper: BookDataToDomainMapper {
    override fun map(id: Int, name: String) = BookDomain.Base(id, name)

}