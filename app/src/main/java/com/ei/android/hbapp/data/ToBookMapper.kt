package com.ei.android.hbapp.data

import com.ei.android.hbapp.core.Abstract


interface ToBookMapper:Abstract.Mapper{
    fun map(id:Int, name: String): BookData

    class Base: ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id,name)
    }

}
