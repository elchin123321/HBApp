package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.data.chapters.ChapterData
import com.ei.android.hbapp.data.chapters.ChapterDataToDomainMapper
import com.ei.android.hbapp.data.chapters.ChaptersDataToDomainMapper

class BaseChaptersDataToDomainMapper(
    private val mapper: ChapterDataToDomainMapper
) : ChaptersDataToDomainMapper() {

    override fun map(data: List<ChapterData>) = ChaptersDomain.Success(data.map { chapterData ->
        chapterData.map(mapper)
    })

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}