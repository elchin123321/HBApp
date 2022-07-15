package com.ei.android.hbapp.data.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB: RealmObject(), Abstract.Object<BookData,ToBookMapper> {
    @PrimaryKey
    var id:Int = -1
    var name:String = ""

    override fun map(mapper: ToBookMapper) =  BookData(id,name)

}
