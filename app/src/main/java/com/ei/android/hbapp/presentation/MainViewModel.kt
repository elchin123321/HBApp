package com.ei.android.hbapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
import com.ei.android.hbapp.domain.BooksDomainToUiMapper
import com.ei.android.hbapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.acl.Owner

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() { //todo interface

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val resultDomain = booksInteractor.fetchBooks()
        withContext(Dispatchers.Main) {
            val resultUi = resultDomain.map(mapper)
            resultUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }
}