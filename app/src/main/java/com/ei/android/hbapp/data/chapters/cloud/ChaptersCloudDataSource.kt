package com.ei.android.hbapp.data.chapters.cloud

import com.ei.android.hbapp.core.TypeTokenProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ChaptersCloudDataSource {
    suspend fun fetchChapters(bookId:Int):List<ChapterCloud>

    class Base(
        private val service:ChaptersService,
        private val gson: Gson,
    ): ChaptersCloudDataSource{

        override suspend fun fetchChapters(bookId:Int): List<ChapterCloud> =
            gson.fromJson(
                service.fetchChapters(bookId).string(),
                object : TypeToken<List<ChapterCloud>>(){}.type
            )


    }
}