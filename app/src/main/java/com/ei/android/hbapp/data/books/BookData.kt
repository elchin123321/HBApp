package com.ei.android.hbapp.data.books

import com.ei.android.hbapp.core.*
import com.ei.android.hbapp.data.books.cache.BookDb
import com.ei.android.hbapp.data.books.cache.BookDataToDbMapper
import com.ei.android.hbapp.domain.books.BookDomain

data class BookData(private val id: Int, private val name: String, private val testament: String) :
    Abstract.Object.ToDb<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    Matcher<TestamentTemp>,
    Save<TestamentTemp> {

    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapBy(mapper: BookDataToDbMapper, db: DbWrapper<BookDb>) =
        mapper.mapToDb(id, name, testament, db)

    override fun matches(arg: TestamentTemp) = arg.matches(testament)
    override fun save(data: TestamentTemp) = data.save(testament)
}

