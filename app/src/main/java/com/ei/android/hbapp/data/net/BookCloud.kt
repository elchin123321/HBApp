package com.ei.android.hbapp.data.net

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Book
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
):Abstract.Object<Book, BookCloudMapper>() {
    override fun map(mapper: BookCloudMapper) = mapper.map(id,name)


}