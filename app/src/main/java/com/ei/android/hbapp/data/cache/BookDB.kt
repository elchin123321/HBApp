package com.ei.android.hbapp.data.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDB: RealmObject(), Abstract.Mappable<Book,BookCacheMapper> {
    @PrimaryKey
    var id:Int = -1
    var name:String = ""

    override fun map(mapper: BookCacheMapper) =  Book(id,name)

}
