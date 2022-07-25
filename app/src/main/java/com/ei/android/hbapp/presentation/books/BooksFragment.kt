package com.ei.android.hbapp.presentation.books

import android.os.Bundle
import android.view.View
import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.BibleApp
import com.ei.android.hbapp.core.Retry
import com.ei.android.hbapp.presentation.BaseFragment

class BooksFragment : BaseFragment() {

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as BibleApp).booksViewModel
    }

    override fun getTitle() = getString(R.string.app_name)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BooksAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchBooks()
            },
            object : BooksAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            },
            object : BooksAdapter.BookListener {
                override fun showBook(id: Int, name:String) = viewModel.showBook(id, name)
            })
        recyclerView?.adapter = adapter
        viewModel.observe(this, {
            adapter.update(it)
        })
        viewModel.init()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveCollapsedStates()
    }
}