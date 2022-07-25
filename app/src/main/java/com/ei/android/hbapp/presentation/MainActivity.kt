package com.ei.android.hbapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ei.android.hbapp.R
import com.ei.android.hbapp.core.BibleApp
import com.ei.android.hbapp.presentation.Screens.Companion.BOOKS_SCREEN
import com.ei.android.hbapp.presentation.Screens.Companion.CHAPTERS_SCREEN
import com.ei.android.hbapp.presentation.books.BooksFragment
import com.ei.android.hbapp.presentation.chapters.ChaptersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as BibleApp).mainViewModel

        viewModel.observe(this, {
            val fragment = when (it) {
                BOOKS_SCREEN -> BooksFragment()
                CHAPTERS_SCREEN -> ChaptersFragment()
                else -> throw IllegalStateException("screen id undefined $it")
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        })
        viewModel.init()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }
}