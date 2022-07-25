package com.ei.android.hbapp.presentation.books


import com.ei.android.hbapp.core.Communication

interface BooksCommunication : Communication<List<BookUi>> {

    class Base : Communication.Base<List<BookUi>>(), BooksCommunication
}
