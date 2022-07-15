package com.ei.android.hbapp.data.cache

import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.BookDataToDbMapper


interface BooksCacheDataSource {
    fun fetchBooks():List<BookDB>
    fun saveBooks(books:List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper:BookDataToDbMapper
    ):BooksCacheDataSource{
        override fun fetchBooks(): List<BookDB> {
            realmProvider.provide().use { realm->
                val booksDb = realm.where(BookDB::class.java).findAll()?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books:List<BookData>) =
            realmProvider.provide().use{realm->
                realm.executeTransaction {
                    books.forEach {book->
                        book.mapTo(mapper,it)

                    }
                }
            }


    }
}