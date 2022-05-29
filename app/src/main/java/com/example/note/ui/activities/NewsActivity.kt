package com.example.note.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.R
import com.example.note.adpaters.NewsRecyclerAdapter
import com.example.note.adpaters.QuoteRecyclerAdapter
import com.example.note.api.models.news.NewsEntity
import com.example.note.databinding.ActivityNewsBinding
import com.example.note.databinding.ActivityQuoteBinding
import com.example.note.viewmodels.NewsViewModel
import com.example.note.viewmodels.QuoteViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setRecyData()


    }

    private fun setRecyData() {
        val layoutManager = LinearLayoutManager(this)
        binding.newsRecy.layoutManager = layoutManager
        adapter = NewsRecyclerAdapter(this)
        binding.newsRecy.adapter = adapter
        viewModel.getNews()
        viewModel.addResult.observe(this) {
            it.body()?.Result?.let { it1 ->
                adapter.setAllData(it1)

            }
        }

    }


}