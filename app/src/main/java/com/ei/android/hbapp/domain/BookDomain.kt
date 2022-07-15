package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.presentation.BookUi

class BookDomain(private val id:Int,private val name: String):Abstract.Object<BookUi,BookDomainToUiMapper> {
    override fun map(mapper: BookDomainToUiMapper): BookUi {
        return mapper.map(id,name)
    }
}