package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.data.chapters.ChaptersDataToDomainMapper
import com.ei.android.hbapp.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper
    ) : ChaptersInteractor {
        override suspend fun fetchChapters() = repository.fetchChapters().map(mapper)
    }
}