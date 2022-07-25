package com.ei.android.hbapp.data.chapters.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.DbWrapper
import com.ei.android.hbapp.data.chapters.ChapterId

interface ChapterDataToDbMapper : Abstract.Mapper {

    fun mapToDb(chapterId: ChapterId, db: DbWrapper<ChapterDb>): ChapterDb

    class Base : ChapterDataToDbMapper {
        override fun mapToDb(chapterId: ChapterId, db: DbWrapper<ChapterDb>) = chapterId.mapToDb(db)
    }
}
