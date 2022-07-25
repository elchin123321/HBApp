package com.ei.android.hbapp.presentation.chapters

import com.ei.android.hbapp.core.ResourceProvider
import com.ei.android.hbapp.domain.ErrorType
import com.ei.android.hbapp.domain.chapters.ChapterDomain
import com.ei.android.hbapp.domain.chapters.ChapterDomainToUiMapper
import com.ei.android.hbapp.domain.chapters.ChaptersDomainToUiMapper

class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper(resourceProvider) {

    override fun map(data: List<ChapterDomain>) = ChaptersUi.Base(data.map { chapterDomain ->
        chapterDomain.map(mapper)
    })

    override fun map(errorType: ErrorType) =
        ChaptersUi.Base(listOf(ChapterUi.Fail(errorMessage(errorType))))
}