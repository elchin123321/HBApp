package com.ei.android.hbapp.core

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeTokenProvider<T> {
    fun provideType():Type{
        return object : TypeToken<T>(){}.type
    }
}