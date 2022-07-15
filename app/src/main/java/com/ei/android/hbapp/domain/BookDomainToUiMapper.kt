package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.presentation.BookUi

interface BookDomainToUiMapper:Abstract.Mapper {
    fun map(id:Int,name:String): BookUi
}