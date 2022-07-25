package com.ei.android.hbapp.data.books

import com.ei.android.hbapp.core.Matcher
import com.ei.android.hbapp.core.Save

interface TestamentTemp : Matcher<String>, Save<String> {
    fun isEmpty(): Boolean

    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(data: String) {
            temp = data
        }

        override fun matches(arg: String) = temp == arg
        override fun isEmpty() = temp.isEmpty()
    }
}