package com.ei.android.hbapp.presentation.chapters

import com.ei.android.hbapp.core.ComparableTextMapper
import com.ei.android.hbapp.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi> {

    class Base(
        private val id: Int, //todo use to get verses
        private val text: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Fail(
        private val message: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)
    }

    object Progress : ChapterUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}