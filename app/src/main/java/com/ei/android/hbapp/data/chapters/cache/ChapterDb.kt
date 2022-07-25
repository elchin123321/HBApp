package com.ei.android.hbapp.data.chapters.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.chapters.ChapterData
import com.ei.android.hbapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChapterDb : RealmObject(), Abstract.Object<ChapterData, ToChapterMapper> {

    /**
     * bookId*1000+chapterId
     */
    @PrimaryKey
    var id: Int = -1
    override fun map(mapper: ToChapterMapper) = mapper.map(id)

}