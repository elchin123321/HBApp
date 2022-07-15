package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.BookDomain

interface BookDataToDomainMapper:Abstract.Mapper {
    fun map(id: Int,name:String, testament:String):BookDomain
}