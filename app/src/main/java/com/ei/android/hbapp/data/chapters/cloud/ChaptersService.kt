package com.ei.android.hbapp.data.chapters.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ChaptersService {
    @GET("books/{id}/chapters")
    suspend fun fetchChapters(
        @Path("id") bookId:Int
    ):ResponseBody

}