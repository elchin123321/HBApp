package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.R
import com.ei.android.hbapp.domain.BookDomain
import com.ei.android.hbapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(id: Int, name: String) = when (id) {
        BookDomain.TestamentType.NEW.getId() -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        BookDomain.TestamentType.OLD.getId() -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUi.Base(id, name)
    }

}