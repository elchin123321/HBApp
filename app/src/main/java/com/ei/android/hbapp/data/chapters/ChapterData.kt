package com.ei.android.hbapp.data.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.DbWrapper
import com.ei.android.hbapp.data.chapters.cache.ChapterDataToDbMapper
import com.ei.android.hbapp.data.chapters.cache.ChapterDb
import com.ei.android.hbapp.domain.chapters.ChapterDomain

data class ChapterData(private val chapterId: ChapterId) :
    Abstract.Object.ToDb<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun mapBy(mapper: ChapterDataToDbMapper, db: DbWrapper<ChapterDb>) =
        mapper.mapToDb(chapterId, db)

    override fun map(mapper: ChapterDataToDomainMapper) = mapper.map(chapterId)
}