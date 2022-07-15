package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.BookDataToDomainMapper
import com.ei.android.hbapp.presentation.BookUi
import com.ei.android.hbapp.presentation.BooksUi
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class BooksDomainTest{
    @Test
    fun test_map(){

        val bookMapper = object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                return BookUi.Base(id,name)
            }

        }
        val domain  = BooksDomain.Success(listOf(
            BookData(1,"genesis","ot"),
            BookData(66,"Revelation","nt"),
        ),
        object :BookDataToDomainMapper{
            override fun map(id: Int, name: String, testament: String) = BookDomain.Base(id,name)

        })

        val actual = domain.map(object :BooksDomainToUiMapper{
            override fun map(books: List<BookDomain>): BooksUi {
               return BooksUi.Success(books,bookMapper)
            }

            override fun map(errorType: ErrorType): BooksUi {
                throw IllegalStateException()
            }

        })

        val expected = BooksUi.Success(
            listOf(
                BookDomain.Testament(BookDomain.TestamentType.OLD),
                BookDomain.Base(1, "genesis"),
                BookDomain.Testament(BookDomain.TestamentType.NEW),
                BookDomain.Base(66, "Revelation")
        ), bookMapper
        )
        assertEquals(expected,actual)
    }
}