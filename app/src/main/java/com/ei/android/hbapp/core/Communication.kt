package com.ei.android.hbapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication<T> : Abstract.Mapper.Data<T, Unit> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    abstract class Base<T> : Communication<T> {
        private val liveData = MutableLiveData<T>()
        override fun map(data: T) {
            liveData.value = data
        }
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)
    }
}