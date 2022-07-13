package com.ei.android.hbapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ei.android.hbapp.domain.BaseBooksDomainToUiMapper
import com.ei.android.hbapp.domain.BooksInteractor
import com.ei.android.hbapp.presentation.BooksCommunication
import com.ei.android.hbapp.presentation.MainViewModel
import com.ei.android.hbapp.presentation.ResourceProvider

class BibleApp:Application() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()


        mainViewModel = MainViewModel(BooksCommunication.Base(),booksInteractor,BaseBooksDomainToUiMapper(BooksCommunication.Base(),ResourceProvider.Base(this)))
    }
}