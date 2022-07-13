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
import com.ei.android.hbapp.data.net.BookService
import retrofit2.Retrofit
import androidx.lifecycle.ViewModel
import com.ei.android.hbapp.domain.BaseBooksDomainToUiMapper
import com.ei.android.hbapp.domain.BooksInteractor
import com.ei.android.hbapp.presentation.BooksCommunication
import com.ei.android.hbapp.presentation.MainViewModel
import com.ei.android.hbapp.presentation.ResourceProvider

class BibleApp:Application() {

    private companion object{
        private val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
                //todo log http calls
            .build()
        val service = retrofit.create(BookService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())


        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper)
        val booksInteractor = BooksInteractor.Base(booksRepository)

        mainViewModel = MainViewModel(BooksCommunication.Base(),booksInteractor,BaseBooksDomainToUiMapper(BooksCommunication.Base(),ResourceProvider.Base(this)))
    }
}