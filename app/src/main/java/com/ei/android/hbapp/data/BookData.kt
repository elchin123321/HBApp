package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.BookDomain

sealed class BookData:Abstract.Object<BookDomain, Abstract.Mapper.Empty>() {
}