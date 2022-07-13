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
import java.security.acl.Owner

class MainViewModel(
    private val communication: BooksCommunication,
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper
):ViewModel() {//TODO interface
    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val result = booksInteractor.fetchBooks().map(mapper)
        (Dispatchers.Main).run{
            result.map(Abstract.Mapper.Empty())
        }

    }

    fun observe(owner: LifecycleOwner,observer: Observer<List<Book>>){
        communication.observeSuccess(owner, observer)
    }
}