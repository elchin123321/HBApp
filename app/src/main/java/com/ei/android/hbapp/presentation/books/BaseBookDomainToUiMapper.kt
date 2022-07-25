package com.ei.android.hbapp.presentation.books

import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.ResourceProvider
import com.ei.android.hbapp.domain.books.BookDomainToUiMapper
import com.ei.android.hbapp.domain.books.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(id: Int, name: String) = when  {
        TestamentType.NEW.matches(id) -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.matches(id) -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUi.Base(id, name)
    }

}