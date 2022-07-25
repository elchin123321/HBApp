package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.chapters.ChapterId
import com.ei.android.hbapp.presentation.chapters.ChapterUi

data class ChapterDomain(private val chapterId: ChapterId) :
    Abstract.Object<ChapterUi, ChapterDomainToUiMapper> {
    override fun map(mapper: ChapterDomainToUiMapper) = mapper.map(chapterId)
}