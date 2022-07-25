package com.ei.android.hbapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ei.android.hbapp.core.Read
import com.ei.android.hbapp.domain.books.BooksDomainToUiMapper
import com.ei.android.hbapp.domain.books.BooksInteractor
import com.ei.android.hbapp.presentation.books.BookUi
import com.ei.android.hbapp.presentation.books.BooksCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val navigator: Read<Int>,
    private val communication: NavigationCommunication
) : ViewModel() {

    fun init() {
        communication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        if (!exit) {
            val newScreen = currentScreen - 1
            communication.map(newScreen)
        }
        return exit
    }
}