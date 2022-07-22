package com.ei.android.hbapp.core

import android.app.Application
import com.ei.android.hbapp.data.*
import com.ei.android.hbapp.data.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.cache.BooksCacheMapper
import com.ei.android.hbapp.data.cache.RealmProvider
import com.ei.android.hbapp.data.net.BooksService
import com.ei.android.hbapp.domain.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.ei.android.hbapp.domain.BaseBooksDataToDomainMapper
import com.ei.android.hbapp.domain.BooksInteractor
import com.ei.android.hbapp.presentation.*
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class BibleApp:Application() {

    private companion object{
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val client = OkHttpClient.Builder()
            .connectTimeout(1,TimeUnit.MINUTES)
            .readTimeout(1,TimeUnit.MINUTES)
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
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base(),BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)


        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper)

        val booksInteractor = BooksInteractor.Base(booksRepository,
            BaseBooksDataToDomainMapper( BaseBookDataToDomainMapper() )
        )
        val communication = BooksCommunication.Base()

        val resourceProvider = ResourceProvider.Base(this)
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider,BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(IdCache.Base(this)))
    }
}