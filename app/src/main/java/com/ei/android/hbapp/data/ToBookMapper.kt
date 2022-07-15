package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.BookDomain


interface ToBookMapper : Abstract.Mapper {
    fun map(id: Int, name: String, testament: String): BookData

    class Base : ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }

}
