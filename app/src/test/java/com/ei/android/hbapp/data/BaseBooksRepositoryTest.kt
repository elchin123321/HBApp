package com.ei.android.hbapp.data

import com.ei.android.hbapp.data.cache.BookDB

open class BaseBooksRepositoryTest {
    protected class TestBookCacheMapper: ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)



    }

    protected class TestBookCloudMapper: ToBookMapper {
        override fun map(id: Int, name: String,testament: String) = BookData(id,name, testament)
    }
}