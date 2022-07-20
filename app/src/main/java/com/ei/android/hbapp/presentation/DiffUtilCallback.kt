package com.ei.android.hbapp.presentation

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList:List<BookUi>,
    private val newList: List<BookUi>
):DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].same(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].sameContent(newList[newItemPosition])
    }

}