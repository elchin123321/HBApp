package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.BookDomain

sealed class BooksData:Abstract.Object<BookDomain, BooksDataToDomainMapper>() {
}