package com.ei.android.hbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.hbapp.core.BibleApp
import com.ei.android.hbapp.presentation.BibleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = (application as BibleApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(object:BibleAdapter.Retry{
            override fun tryAgain() {
                viewModel.fetchBooks()
            }
        })
        recyclerView.adapter = adapter

        viewModel.observe(this, {
            Log.d("Count", it.size.toString())
            adapter.update(it)
        })
        viewModel.fetchBooks()
        // TODO: observe fail
    }
}