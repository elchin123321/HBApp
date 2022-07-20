package com.ei.android.hbapp.presentation

interface UiDataCache {
    fun changeState(id:Int):List<BookUi>
    fun cache(list: List<BookUi>)

    class Base:UiDataCache{
        private val cachedList = ArrayList<BookUi>()


        override fun cache(list: List<BookUi>) {
            cachedList.clear()
            cachedList.addAll(list)
        }
        override fun changeState(id: Int): List<BookUi> {
            val newList = ArrayList<BookUi>()
            val item =  cachedList.find{
                it.matches(id)

            }
            var add = false
            cachedList.forEachIndexed{index,book->
                if(book is BookUi.Testament){
                    if(item == book){
                        val element = book.changeState()
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.isCollapsed()
                    }else{
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                }else{
                    if(add)newList.add(book)
                }

            }

            return newList
        }
    }
}
