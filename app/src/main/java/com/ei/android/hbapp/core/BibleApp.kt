package com.ei.android.hbapp.core

import android.app.Application
import com.ei.android.hbapp.data.books.*
import com.ei.android.hbapp.data.books.cache.BookDataToDbMapper
import com.ei.android.hbapp.data.books.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.books.cache.BooksCacheMapper
import com.ei.android.hbapp.data.books.cache.RealmProvider
import com.ei.android.hbapp.data.books.cloud.BooksCloudDataSource
import com.ei.android.hbapp.data.books.cloud.BooksCloudMapper
import com.ei.android.hbapp.data.books.cloud.BooksService
import com.ei.android.hbapp.data.chapters.ChapterIdToUiMapper
import com.ei.android.hbapp.data.chapters.ChaptersRepository
import com.ei.android.hbapp.data.chapters.ToChapterMapper
import com.ei.android.hbapp.data.chapters.cache.ChapterDataToDbMapper
import com.ei.android.hbapp.data.chapters.cache.ChaptersCacheDataSource
import com.ei.android.hbapp.data.chapters.cache.ChaptersCacheMapper
import com.ei.android.hbapp.data.chapters.cloud.ChaptersCloudDataSource
import com.ei.android.hbapp.data.chapters.cloud.ChaptersCloudMapper
import com.ei.android.hbapp.data.chapters.cloud.ChaptersService
import com.ei.android.hbapp.domain.books.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.ei.android.hbapp.domain.books.BaseBooksDataToDomainMapper
import com.ei.android.hbapp.domain.books.BooksInteractor
import com.ei.android.hbapp.domain.chapters.BaseChapterDataToDomainMapper
import com.ei.android.hbapp.domain.chapters.BaseChaptersDataToDomainMapper
import com.ei.android.hbapp.domain.chapters.ChaptersInteractor
import com.ei.android.hbapp.presentation.*
import com.ei.android.hbapp.presentation.books.*
import com.ei.android.hbapp.presentation.chapters.BaseChapterDomainToUiMapper
import com.ei.android.hbapp.presentation.chapters.BaseChaptersDomainToUiMapper
import com.ei.android.hbapp.presentation.chapters.ChaptersCommunication
import com.ei.android.hbapp.presentation.chapters.ChaptersViewModel
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var booksViewModel: BooksViewModel
    lateinit var chaptersViewModel: ChaptersViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val service = retrofit.create(BooksService::class.java)

        val gson = Gson()
        val cloudDataSource = BooksCloudDataSource.Base(service, gson)
        val realmProvider = RealmProvider.Base()
        val cacheDataSource =
            BooksCacheDataSource.Base(realmProvider, BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)
        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )
        val communication = BooksCommunication.Base()
        val resourceProvider = ResourceProvider.Base(this)
        val bookCache = BookCache.Base(this)
        val chaptersRepository = ChaptersRepository.Base(
            ChaptersCloudDataSource.Base(
                retrofit.create(ChaptersService::class.java),
                gson
            ),
            ChaptersCacheDataSource.Base(realmProvider, ChapterDataToDbMapper.Base()),
            ChaptersCloudMapper.Base(ToChapterMapper.Cloud(bookCache)),
            ChaptersCacheMapper.Base(ToChapterMapper.Db(bookCache)),
            bookCache
        )
        val chaptersInteractor = ChaptersInteractor.Base(
            chaptersRepository,
            BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
        )

        val navigator = Navigator.Base(this)
        val navigationCommunication = NavigationCommunication.Base()
        mainViewModel = MainViewModel(navigator, navigationCommunication)

        booksViewModel = BooksViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(CollapsedIdsCache.Base(this)),
            bookCache,
            navigator,
            navigationCommunication
        )

        chaptersViewModel = ChaptersViewModel(
            chaptersInteractor,
            ChaptersCommunication.Base(),
            BaseChaptersDomainToUiMapper(
                BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(resourceProvider)),
                resourceProvider
            ),
            navigator,
            bookCache
        ) //todo IoC
    }
}