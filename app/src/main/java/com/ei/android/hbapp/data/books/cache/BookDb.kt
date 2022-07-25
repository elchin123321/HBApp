package com.ei.android.hbapp.data.books.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.books.BookData
import com.ei.android.hbapp.data.books.CommonBookData
import com.ei.android.hbapp.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb: RealmObject(), CommonBookData {
    @PrimaryKey
    var id:Int = -1
    var name:String = ""
    var testament:String=""

    override fun map(mapper: ToBookMapper) =  mapper.map(id,name,testament)

}
