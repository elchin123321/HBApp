package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.cache.DbWrapper
import io.realm.Realm

interface ToBookDb<T, M: Abstract.Mapper>{


    fun mapTo(mapper:M,db: DbWrapper):T
}