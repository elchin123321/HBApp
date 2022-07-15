package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.core.Abstract

sealed class BookUi:Abstract.Object<Unit,BookUi.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    object Progress: BookUi()


    open class Info(
        private val id:Int, //todo use for getting chapters
        private val name:String
    ):BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    class Base(
        private val id:Int, //todo use for getting chapters
        private val name:String
    ):Info(id, name)

    class Testament(
        private val id:Int, //todo use for getting chapters
        private val name:String
    ):Info(id, name)

    class Fail(
        private val message:String
    ):BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(message)
    }
    //todo improve later
    interface StringMapper:Abstract.Mapper{
        fun map(text:String)
    }
}