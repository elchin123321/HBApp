package com.ei.android.hbapp.data.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Read

interface ToChapterMapper : Abstract.Mapper.Data<Int, ChapterData> {

    abstract class Base(private val bookCache: Read<Pair<Int, String>>) : ToChapterMapper {
        override fun map(data: Int): ChapterData {
            val realId = realId()
            return ChapterData(
                ChapterId.Base(
                    bookCache.read().first,
                    if (realId) data else 0,
                    if (realId) 0 else data
                )
            )
        }

        protected abstract fun realId(): Boolean
    }

    class Cloud(bookCache: Read<Pair<Int, String>>) : ToChapterMapper.Base(bookCache) {
        override fun realId() = true
    }

    class Db(bookCache: Read<Pair<Int, String>>) : ToChapterMapper.Base(bookCache) {
        override fun realId() = false
    }
}