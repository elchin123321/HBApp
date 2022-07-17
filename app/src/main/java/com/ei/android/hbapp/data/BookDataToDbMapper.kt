package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.cache.BookDB
import com.ei.android.hbapp.data.cache.DbWrapper
import io.realm.Realm

interface BookDataToDbMapper:Abstract.Mapper {
    fun mapToDb(id:Int, name: String, testament:String, db: DbWrapper):BookDB

    class Base: BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament:String, db: DbWrapper):BookDB {
            val bookDB = db.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB

        }
    }
}