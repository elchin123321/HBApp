package com.ei.android.hbapp.presentation.chapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.BibleApp
import com.ei.android.hbapp.core.Retry
import com.ei.android.hbapp.presentation.BaseFragment

class ChaptersFragment : BaseFragment() {

    private lateinit var viewModel: ChaptersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as BibleApp).chaptersViewModel
    }

    override fun getTitle() = viewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchChapters()
        })
        viewModel.observeChapters(this, {
            adapter.update(it)
        })
        recyclerView?.adapter = adapter

        viewModel.init()
    }
}