package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.domain.BookDomain
import io.realm.Realm

class BookData(private val id:Int, private val name:String,private val testament:String):
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    ToBookDb<BookDB, BookDataToDbMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id,name,testament)
    override fun mapTo(mapper: BookDataToDbMapper,realm:Realm) = mapper.mapToDb(id,name, testament,realm)

    fun compare(temp: TestamentTemp) = temp.matches(testament)
    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

interface TestamentTemp{
    fun save(testament: String)
    fun matches(testament: String):Boolean
    fun isEmpty():Boolean

    class Base:TestamentTemp{
        private var temp:String = ""

        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String) = temp == testament

        override fun isEmpty() = temp.isEmpty()


    }

}


interface ToBookDb<T, M:Abstract.Mapper>{


    fun mapTo(mapper:M,realm: Realm):T
}