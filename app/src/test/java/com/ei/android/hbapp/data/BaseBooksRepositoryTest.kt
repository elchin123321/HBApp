package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.data.cache.BookCacheMapper
import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.data.net.BookCloudMapper

open class BaseBooksRepositoryTest {
    protected class TestBookCacheMapper: ToBookMapper {
        override fun map(id: Int, name: String) = Book(id, name)

    }

    protected class TestBookCloudMapper: ToBookMapper {
        override fun map(id: Int, name: String) = Book(id,name)
    }
}