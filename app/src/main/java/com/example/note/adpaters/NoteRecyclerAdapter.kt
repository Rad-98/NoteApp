package com.example.note.adpaters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ActivityShowNotesBinding
import com.example.note.database.entity.NoteEntity
import com.example.note.databinding.ItemRecyclerNotesBinding

class NoteRecyclerAdapter(private val context: Context) :RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder>() {

    private lateinit var binding: ActivityShowNotesBinding
    private lateinit var onItemClickListener: OnItemClickListener
    private val dataList = ArrayList<NoteEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = ItemRecyclerNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = dataList[position]
        holder.etTitle.text = item.title
        holder.btnDelete.setOnClickListener{
            onItemClickListener.onItemDelete(item)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemEdit(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(list: List<NoteEntity>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class NoteViewHolder( binding: ItemRecyclerNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        var btnDelete: ImageView = binding.btnDelete
        var etTitle: TextView = binding.txt1

    }


    interface OnItemClickListener {
        fun onItemDelete(noteEntity: NoteEntity)
        fun onItemEdit(noteEntity: NoteEntity)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

}
