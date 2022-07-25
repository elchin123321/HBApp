package com.ei.android.hbapp.data.chapters.cache

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.chapters.ChapterData
import com.ei.android.hbapp.data.chapters.ToChapterMapper

interface ChaptersCacheMapper:Abstract.Mapper.Data<List<ChapterDb>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper): ChaptersCacheMapper {
        override fun map(data: List<ChapterDb>) = data.map{ chapterDb ->
            chapterDb.map(mapper)
        }

    }

}
