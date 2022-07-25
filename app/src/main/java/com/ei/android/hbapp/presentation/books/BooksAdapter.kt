package com.ei.android.hbapp.presentation.books


import android.view.View
import android.view.ViewGroup
import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.*

class BooksAdapter(
    private val retry: Retry,
    private val collapseListener: CollapseListener,
    private val bookListener: BookListener
) : BaseAdapter<BookUi, BaseViewHolder<BookUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Testament -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BookUi> = when (viewType) {
        0 -> BooksViewHolder.Base(R.layout.text_layout.makeView(parent), bookListener)
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BooksViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        else -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    abstract class BooksViewHolder(view: View) : BaseViewHolder<BookUi>(view) {

        abstract class Info(view: View) : BooksViewHolder(view) {
            protected val name: CustomTextView = itemView.findViewById(R.id.textView)
            override fun bind(item: BookUi) = item.map(name)
        }

        class Base(view: View, private val bookListener: BookListener) : Info(view) {
            override fun bind(item: BookUi) {
                super.bind(item)
                name.setOnClickListener {
                    item.open(bookListener)
                }
            }
        }

        class Testament(view: View, private val collapse: CollapseListener) : Info(view) {
            private val collapseView = itemView.findViewById<CollapseView>(R.id.collapseView)
            override fun bind(item: BookUi) {
                super.bind(item)
                itemView.setOnClickListener {
                    item.collapseOrExpand(collapse)
                }
                item.showCollapsed(collapseView)
            }
        }
    }

    interface CollapseListener {
        fun collapseOrExpand(id: Int)
    }

    interface BookListener {
        fun showBook(id: Int, name: String)
    }
}