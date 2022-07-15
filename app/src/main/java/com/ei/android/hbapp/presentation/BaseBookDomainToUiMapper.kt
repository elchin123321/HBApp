package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper:BookDomainToUiMapper {
    override fun map(id: Int, name: String) = BookUi.Base(id,name)

}