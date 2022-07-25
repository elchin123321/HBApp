package com.ei.android.hbapp.data.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData:Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    data class Success(private val chapters:List<ChapterData>):ChaptersData(){
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(chapters)

    }
    data class Fail(private val e:Exception):ChaptersData(){

        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(e)

    }

}
