package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BookDataToDomainMapper

class BaseBookDataToDomainMapper:BookDataToDomainMapper {
    override fun map(id: Int, name: String, testament:String) = BookDomain.Base(id,name)

}