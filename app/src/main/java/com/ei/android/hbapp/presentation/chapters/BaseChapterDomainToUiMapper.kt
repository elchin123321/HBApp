package com.ei.android.hbapp.presentation.chapters

import com.ei.android.hbapp.data.chapters.ChapterId
import com.ei.android.hbapp.data.chapters.ChapterIdToUiMapper
import com.ei.android.hbapp.domain.chapters.ChapterDomainToUiMapper

class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper) :
    ChapterDomainToUiMapper {

    override fun map(data: ChapterId) = data.map(mapper)
}