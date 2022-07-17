package com.ei.android.hbapp.domain

import com.ei.android.hbapp.data.BookData
import com.ei.android.hbapp.data.BookDataToDomainMapper
import com.ei.android.hbapp.data.BooksDataToDomainMapper
import com.ei.android.hbapp.data.TestamentTemp
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(
    private val mapper: BookDataToDomainMapper):BooksDataToDomainMapper {
    override fun map(books: List<BookData>):BooksDomain  {
        val data = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach{bookData ->
            if (!bookData.compare(temp)){
                if(temp.isEmpty())
                    data.add(BookDomain.Testament(TestamentType.OLD))
                else
                    data.add(BookDomain.Testament(TestamentType.NEW))
                bookData.saveTestament(temp)
            }
            data.add(bookData.map(mapper))

        }
        return BooksDomain.Success(data)
    }


    override fun map(e: Exception) = BooksDomain.Fail(
            when(e){
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException ->ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )


}