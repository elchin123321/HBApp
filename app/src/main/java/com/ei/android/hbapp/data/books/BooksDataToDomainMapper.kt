package com.ei.android.hbapp.data.books

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.books.BooksDomain

abstract class BooksDataToDomainMapper:
    Abstract.Mapper.DataToDomain.Base<List<BookData>, BooksDomain>()