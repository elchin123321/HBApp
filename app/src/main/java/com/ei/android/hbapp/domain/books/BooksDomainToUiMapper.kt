package com.ei.android.hbapp.domain.books

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.ResourceProvider
import com.ei.android.hbapp.domain.ErrorType
import com.ei.android.hbapp.presentation.books.BooksUi

abstract class BooksDomainToUiMapper(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<BookDomain>, BooksUi>(resourceProvider)