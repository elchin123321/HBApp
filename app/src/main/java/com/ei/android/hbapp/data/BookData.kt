package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.domain.BookDomain
import io.realm.Realm

class BookData(private val id:Int, private val name:String):
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    ToBookDb<BookDB, BookDataToDbMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id,name)
    override fun mapTo(mapper: BookDataToDbMapper,realm:Realm) = mapper.mapToDb(id,name,realm)
}

interface ToBookDb<T, M:Abstract.Mapper>{


    fun mapTo(mapper:M,realm: Realm):T
}