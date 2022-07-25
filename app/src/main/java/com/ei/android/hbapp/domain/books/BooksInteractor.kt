package com.ei.android.hbapp.domain.books

import com.ei.android.hbapp.data.books.BooksDataToDomainMapper
import com.ei.android.hbapp.data.books.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
        ): BooksInteractor {
        override suspend fun fetchBooks() =  booksRepository.fetchBooks().map(mapper)

    }
}