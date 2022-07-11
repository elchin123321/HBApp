package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.presentation.BookUI

sealed class BookDomain: Abstract.Object<BookUI, Abstract.Mapper.Empty>() {
}