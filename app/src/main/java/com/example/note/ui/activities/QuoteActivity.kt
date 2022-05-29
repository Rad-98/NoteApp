package com.example.note.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.adpaters.QuoteRecyclerAdapter
import com.example.note.api.models.quotes.QouteEntity
import com.example.note.databinding.ActivityQuoteBinding
import com.example.note.viewmodels.QuoteViewModel

class QuoteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityQuoteBinding
    private lateinit var viewModel: QuoteViewModel
    private lateinit var adapter: QuoteRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        setRecyData()

            adapter.setOnItemClickListener(object :QuoteRecyclerAdapter.OnItemClickListener{
            override fun onItemClicked(qouteEntity: QouteEntity) {
                Toast.makeText(this@QuoteActivity,qouteEntity.author,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setRecyData() {
        val layoutManager = LinearLayoutManager(this)
        binding.quoteRecy.layoutManager = layoutManager
        adapter = QuoteRecyclerAdapter(this)
        binding.quoteRecy.adapter = adapter
        viewModel.getQuote()
        viewModel.addResult.observe(this) {
            it.body()?.results?.let { it1 -> adapter.setAllData(it1) }
        }
    }

}