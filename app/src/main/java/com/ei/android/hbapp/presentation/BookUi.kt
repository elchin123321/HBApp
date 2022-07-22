package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.Matcher

sealed class BookUi:Abstract.Object<Unit,BookUi.StringMapper>, Matcher<Int>, Collapsing, Comparing {
    override fun map(mapper: StringMapper) = Unit

    override fun matches(arg: Int) = false
    open fun changeState():BookUi = Progress

    open fun saveId(cache: IdCache) = Unit

    object Progress: BookUi()


    open class Info(
        protected open val id:Int, //todo use for getting chapters
        protected open val name:String
    ):BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(name)
        override fun matches(arg: Int) = arg == id

    }

    class Base(id:Int,name:String):Info(id, name){
        override fun same(bookUi: BookUi) = bookUi is Base && id == bookUi.id

        override fun sameContent(bookUi: BookUi) = if(bookUi is Base){
            name == bookUi.name
        }else false
    }

    data class Testament(override val id:Int,override val name:String, private val collapsed:Boolean = false):Info(id, name){
        override fun collapseOrExpand(listener: BibleAdapter.CollapseListener) {
            listener.collapseOrExpand(id)
        }

        override fun showCollapsed(mapper: CollapseMapper) {
            mapper.show(collapsed )
        }

        override fun same(bookUi: BookUi) = bookUi is Testament && id == bookUi.id

        override fun sameContent(bookUi: BookUi) = if(bookUi is Testament){
            name == bookUi.name && collapsed == bookUi.collapsed
        }else false


        override fun changeState() = Testament(id,name, !collapsed)
        override fun isCollapsed() = collapsed

        override fun saveId(cache: IdCache) {
                cache.save(id)
        }


    }

    class Fail(
        private val message:String
    ):BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(message)

        override fun same(bookUi: BookUi) = sameContent(bookUi)

        override fun sameContent(bookUi: BookUi) = if(bookUi is Fail){
            message == bookUi.message
        }else false
    }
    //todo improve later
    interface StringMapper:Abstract.Mapper{
        fun map(text:String)
    }
    interface CollapseMapper: Abstract.Mapper{
        fun show(collapsed: Boolean)
    }
}

interface Collapsing{
     fun collapseOrExpand(listener: BibleAdapter.CollapseListener) = Unit
     fun showCollapsed(mapper: BookUi.CollapseMapper) = Unit
     fun isCollapsed():Boolean = false
}

interface Comparing{
     fun sameContent(bookUi: BookUi) = false
     fun same(bookUi: BookUi) = false
}