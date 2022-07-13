package com.ei.android.hbapp.core

import android.app.Application
import com.ei.android.hbapp.data.BooksCloudDataSource
import com.ei.android.hbapp.data.BooksCloudMapper
import com.ei.android.hbapp.data.BooksRepository
import com.ei.android.hbapp.data.cache.BookCacheMapper
import com.ei.android.hbapp.data.cache.BooksCacheDataSource
import com.ei.android.hbapp.data.cache.BooksCacheMapper
import com.ei.android.hbapp.data.cache.RealmProvider
import com.ei.android.hbapp.data.net.BookCloudMapper
import com.ei.android.hbapp.data.net.BooksService
import retrofit2.Retrofit
import com.ei.android.hbapp.domain.BaseBooksDataToDomainMapper
import com.ei.android.hbapp.presentation.BaseBooksDomainToUiMapper
import com.ei.android.hbapp.domain.BooksInteractor
import com.ei.android.hbapp.presentation.BooksCommunication
import com.ei.android.hbapp.presentation.MainViewModel
import com.ei.android.hbapp.presentation.ResourceProvider
import io.realm.Realm

class BibleApp:Application() {

    private companion object{
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())


        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper)

        val booksInteractor = BooksInteractor.Base(booksRepository,BaseBooksDataToDomainMapper())
        val communication = BooksCommunication.Base()

        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(communication,ResourceProvider.Base(this)),
            communication)
    }
}