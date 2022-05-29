package com.example.note.adpaters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.note.R
import com.example.note.api.models.news.NewsEntity
import com.example.note.databinding.ActivityQuoteBinding
import com.example.note.databinding.ItemRecyclerNewsBinding

class NewsRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    private val dataList = ArrayList<NewsEntity>()
    private lateinit var onItemClickListener: OnItemClickListener



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemRecyclerNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = dataList[position]

        holder.txtTitle.text = item.title
        holder.txtLink.text = item.link
        holder.txtSummary.text = item.summary

        Glide
            .with(context)
            .load("https://picsum.photos/120?rand="+System.currentTimeMillis())
            .centerCrop()
            .into(holder.txtImage)

        holder.txtLink.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(item.link)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(list: List<NewsEntity>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(binding: ItemRecyclerNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        val txtImage:ImageView = binding.newsImage
        val txtTitle:TextView = binding.newsTitle
        val txtLink:TextView = binding.newsLink
        val txtSummary:TextView = binding.newsDesc


    }

    interface OnItemClickListener {
        fun onItemClicked(newsEntity: NewsEntity)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener

    }

}