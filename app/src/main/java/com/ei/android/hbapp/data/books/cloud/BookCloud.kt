package com.ei.android.hbapp.data.books.cloud

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.books.BookData
import com.ei.android.hbapp.data.books.ToBookMapper
import com.google.gson.annotations.SerializedName

/**
 *
 *
 */
data class BookCloud(
    @SerializedName("id")
    private val id:Int,
    @SerializedName("name")
    private val name:String,
    @SerializedName("testament")
    private val testament:String
):Abstract.Object<BookData, ToBookMapper> {
    override fun map(mapper: ToBookMapper) = mapper.map(id,name,testament)


}