package com.ei.android.hbapp.data.chapters.cloud

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.chapters.ChapterData
import com.ei.android.hbapp.data.chapters.ToChapterMapper
import com.google.gson.annotations.SerializedName
import java.lang.IllegalStateException

class ChapterCloud(
    @SerializedName("id")
    private val id: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper) = mapper.map(id)


}
