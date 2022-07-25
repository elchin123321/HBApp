package com.ei.android.hbapp.data.books.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.DbWrapper

interface BookDataToDbMapper:Abstract.Mapper {
    fun mapToDb(id:Int, name: String, testament:String, db: DbWrapper<BookDb>): BookDb

    class Base: BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament:String, db: DbWrapper<BookDb>): BookDb {
            val bookDB = db.createObject(id)
            bookDB.name = name
            bookDB.testament = testament
            return bookDB

        }
    }
}