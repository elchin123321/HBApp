package com.ei.android.hbapp.data.chapters.cloud

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.chapters.ChapterData
import com.ei.android.hbapp.data.chapters.ToChapterMapper

interface ChaptersCloudMapper :Abstract.Mapper{

    fun map(chapters:List<ChapterCloud>,bookId:Int):List<ChapterData>

    class Base(private val mapper: ToChapterMapper):ChaptersCloudMapper{
        override fun map(chapters: List<ChapterCloud>,bookId:Int) = chapters.map{chapterCloud ->
            chapterCloud.map(mapper)
        }

    }

}
