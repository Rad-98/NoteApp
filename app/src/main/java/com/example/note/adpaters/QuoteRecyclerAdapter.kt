package com.example.note.adpaters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.api.models.quotes.QouteEntity
import com.example.note.databinding.ActivityQuoteBinding
import com.example.note.databinding.ItemRecyvlerQouteBinding

class QuoteRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<QuoteRecyclerAdapter.QuoteViewHolder>() {

    private lateinit var binding: ActivityQuoteBinding
    private val dataList = ArrayList<QouteEntity>()
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = ItemRecyvlerQouteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = dataList[position]

        holder.txtName.text = item.author
        holder.txtQuote.text = item.content
        holder.txtDate.text = "Date modified "+item.dateModified + " Date added" + item.dateAdded


        holder.txtQuote.setOnClickListener{
            onItemClickListener.onItemClicked(item)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(list: List<QouteEntity>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(binding: ItemRecyvlerQouteBinding) : RecyclerView.ViewHolder(binding.root) {

        val txtName:TextView = binding.txtName
        val txtQuote:TextView = binding.txtQuote
        val txtDate:TextView = binding.txtDate


    }

    interface OnItemClickListener {
        fun onItemClicked(qouteEntity: QouteEntity)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener

    }

}