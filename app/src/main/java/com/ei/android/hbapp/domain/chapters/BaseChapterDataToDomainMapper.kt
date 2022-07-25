package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.data.chapters.ChapterDataToDomainMapper
import com.ei.android.hbapp.data.chapters.ChapterId

class BaseChapterDataToDomainMapper : ChapterDataToDomainMapper {
    override fun map(data: ChapterId) = ChapterDomain(data)
}