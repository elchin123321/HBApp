package com.ei.android.hbapp.data.books.cache

import com.ei.android.hbapp.core.DbWrapper
import com.ei.android.hbapp.core.Read
import com.ei.android.hbapp.core.Save
import com.ei.android.hbapp.data.books.BookData
import io.realm.Realm


interface BooksCacheDataSource : Save<List<BookData>>, Read<List<BookDb>> {

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper,
    ) : BooksCacheDataSource {

        override fun read(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun save(data: List<BookData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                data.forEach { book ->
                    book.mapBy(mapper, BookDbWrapper(it))
                }
            }
        }

        private inner class BookDbWrapper(realm: Realm) : DbWrapper.Base<BookDb>(realm) {
            override fun dbClass() = BookDb::class.java
        }
    }
}