package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.ErrorType
import com.ei.android.hbapp.presentation.chapters.ChaptersUi

sealed class ChaptersDomain : Abstract.Object<ChaptersUi, ChaptersDomainToUiMapper> {

    data class Success(
        private val chapters: List<ChapterDomain>
    ) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper) = mapper.map(chapters)
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper) = mapper.map(errorType)
    }
}