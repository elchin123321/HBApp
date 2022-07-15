package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.cache.BookDB
import io.realm.Realm

interface BookDataToDbMapper:Abstract.Mapper {
    fun mapToDb(id:Int, name: String, testament:String, realm: Realm):BookDB

    class Base: BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament:String, realm: Realm):BookDB {
            val bookDB = realm.createObject(BookDB::class.java, id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB

        }
    }
}