package com.ei.android.hbapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ei.android.hbapp.core.Abstract

interface BooksCommunication:Abstract.Mapper {
    fun map(books: List<BookUi>)

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>)

    class Base : BooksCommunication {
        private val listSuccessLiveData = MutableLiveData<List<BookUi>>()


        override fun map(books: List<BookUi>) {
            listSuccessLiveData.value = books
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
            listSuccessLiveData.observe(owner, observer)
        }


    }
}
