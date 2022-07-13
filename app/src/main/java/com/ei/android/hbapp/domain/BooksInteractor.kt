package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BooksDataToDomainMapper
import com.ei.android.hbapp.data.BooksRepository
import com.ei.android.hbapp.presentation.BooksUI

interface BooksInteractor {

    suspend fun fetchBooks():BookDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
        ):BooksInteractor {
        override suspend fun fetchBooks() =  booksRepository.fetchBooks().map(mapper)

    }
}