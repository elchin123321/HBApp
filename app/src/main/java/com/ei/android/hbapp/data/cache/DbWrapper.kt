package com.ei.android.hbapp.data.cache

import io.realm.Realm

interface DbWrapper {
    fun createObject(id:Int):BookDB

    class Base(private val realm: Realm):DbWrapper{
        override fun createObject(id: Int): BookDB {
            return realm.createObject(BookDB::class.java,id)
        }

    }
}