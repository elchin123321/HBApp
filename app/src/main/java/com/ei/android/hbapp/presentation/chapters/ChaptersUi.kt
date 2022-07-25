package com.ei.android.hbapp.presentation.chapters

import com.ei.android.hbapp.core.Abstract

sealed class ChaptersUi : Abstract.Object<Unit, ChaptersCommunication> {

    class Base(private val chapters: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(chapters)
    }
}