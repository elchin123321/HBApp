package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.data.cache.DbWrapper
import com.ei.android.hbapp.domain.BookDomain
import io.realm.Realm

class BookData(private val id:Int, private val name:String,private val testament:String):
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    ToBookDb<BookDB, BookDataToDbMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id,name,testament)
    override fun mapTo(mapper: BookDataToDbMapper,db:DbWrapper) = mapper.mapToDb(id,name, testament,db)

    fun compare(temp: TestamentTemp) = temp.matches(testament)
    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

