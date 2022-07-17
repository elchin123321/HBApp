package com.ei.android.hbapp.domain

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Matcher
import com.ei.android.hbapp.presentation.BookUi

enum class TestamentType(private val id:Int):Abstract.Object<BookUi,BookDomainToUiMapper>,
    Matcher<Int> {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun matches(arg:Int) = id == arg
    override fun map(mapper: BookDomainToUiMapper) = mapper.map(id,name)
}

