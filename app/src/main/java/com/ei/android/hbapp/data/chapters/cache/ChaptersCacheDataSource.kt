package com.ei.android.hbapp.data.chapters.cache

import com.ei.android.hbapp.core.DbWrapper
import com.ei.android.hbapp.core.Save
import com.ei.android.hbapp.data.books.cache.RealmProvider
import com.ei.android.hbapp.data.chapters.ChapterData
import com.ei.android.hbapp.data.chapters.ChapterId
import io.realm.Realm

interface ChaptersCacheDataSource : Save<List<ChapterData>> {

    fun fetchChapters(bookId: Int): List<ChapterDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ChapterDataToDbMapper
    ) : ChaptersCacheDataSource {

        override fun fetchChapters(bookId: Int): List<ChapterDb> {
            val chapterId = ChapterId.Base(bookId)
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .between("id", chapterId.min(), chapterId.max())
                    .findAll()
                return realm.copyFromRealm(chapters)
            }
        }

        override fun save(data: List<ChapterData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { chapter ->
                        chapter.mapBy(mapper, ChapterDbWrapper(realm))
                    }
                }
            }
        }

        private inner class ChapterDbWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}
