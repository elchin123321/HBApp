package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BookDataToDomainMapper

class BaseBookDataToDomainMapper:BookDataToDomainMapper {
    override fun map(id: Int, name: String):BookDomain{
        return BookDomain(id,name)
    }
}