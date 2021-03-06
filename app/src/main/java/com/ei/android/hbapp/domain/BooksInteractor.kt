package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BooksDataToDomainMapper
import com.ei.android.hbapp.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks():BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
        ):BooksInteractor {
        override suspend fun fetchBooks() =  booksRepository.fetchBooks().map(mapper)

    }
}