package com.ei.android.hbapp.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface BooksService {
    @GET("books")
    suspend fun fetchBooks():ResponseBody
}