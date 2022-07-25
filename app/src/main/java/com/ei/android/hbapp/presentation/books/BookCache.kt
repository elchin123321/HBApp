package com.ei.android.hbapp.presentation.books

import android.content.Context
import com.ei.android.hbapp.core.Read
import com.ei.android.hbapp.core.Save

interface BookCache : Save<Pair<Int, String>>, Read<Pair<Int, String>> {

    class Base(context: Context) : BookCache {
        private val sharedPreferences =
            context.getSharedPreferences(BOOK_ID_FILENAME, Context.MODE_PRIVATE)

        override fun read() = Pair(
            sharedPreferences.getInt(BOOK_ID_KEY, 0),
            sharedPreferences.getString(BOOK_NAME_KEY, "") ?: ""
        )

        override fun save(data: Pair<Int, String>) =
            sharedPreferences.edit()
                .putInt(BOOK_ID_KEY, data.first)
                .putString(BOOK_NAME_KEY, data.second)
                .apply()

        private companion object {
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_ID_KEY = "bookIdKey"
            const val BOOK_NAME_KEY = "bookNameKey"
        }
    }
}