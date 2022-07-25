package com.ei.android.hbapp.domain.books

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.presentation.books.BookUi

interface BookDomainToUiMapper:Abstract.Mapper {
    fun map(id:Int,name:String): BookUi
}