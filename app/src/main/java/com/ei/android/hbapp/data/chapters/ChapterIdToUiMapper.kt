package com.ei.android.hbapp.data.chapters

import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.ResourceProvider
import com.ei.android.hbapp.presentation.chapters.ChapterUi

interface ChapterIdToUiMapper : Abstract.Mapper {
    fun map(generatedId: Int, realId: Int): ChapterUi

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper {
        override fun map(generatedId: Int, realId: Int) =
            ChapterUi.Base(generatedId, resourceProvider.getString(R.string.chapter_number, realId))
    }
}