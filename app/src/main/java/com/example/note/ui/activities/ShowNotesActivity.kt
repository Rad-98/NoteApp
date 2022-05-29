package com.example.note.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.adpaters.NoteRecyclerAdapter
import com.example.note.databinding.ActivityShowNotesBinding
import com.example.note.database.entity.NoteEntity
import com.example.note.viewmodels.ShowNotesViewModel

class ShowNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowNotesBinding
    private lateinit var adapter: NoteRecyclerAdapter
    private lateinit var viewModel: ShowNotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ShowNotesViewModel::class.java)
        setRecyclerData()


        adapter.setOnItemClickListener(object : NoteRecyclerAdapter.OnItemClickListener {
            override fun onItemDelete(noteEntity: NoteEntity) {
                showBasicDialog(noteEntity)
            }
            val addNoteResultActivity =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        viewModel.showNotes()
                    }
                }

            override fun onItemEdit(noteEntity: NoteEntity) {
                val editIntent = Intent(this@ShowNotesActivity, AddOrEditNoteActivity::class.java)
                editIntent.putExtra("EDIT_NOTE", "edit note")
                editIntent.putExtra("ID", noteEntity.id)
                editIntent.putExtra("TITLE", noteEntity.title)
                editIntent.putExtra("DESC", noteEntity.description)
                addNoteResultActivity.launch(editIntent)
            }

        })

        binding.addNote.setOnClickListener{
            startActivity(Intent(this,AddOrEditNoteActivity::class.java))
        }
    }

    private fun setRecyclerData() {
        val layoutManager = LinearLayoutManager(this)
        binding.noteRecycler.layoutManager = layoutManager
        adapter = NoteRecyclerAdapter(this)
        binding.noteRecycler.adapter = adapter
        viewModel.showNotes()
        viewModel.result.observe(this) {
            adapter.setAllData(it)
        }
    }

    fun showBasicDialog(noteEntity:NoteEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete")
        builder.setMessage("Do you want to delete this note?")
        builder.setPositiveButton("Delete") { dialog, which ->
            viewModel.deleteNote(noteEntity.id)
            viewModel.showNotes()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            return@setNegativeButton
        }
        builder.show()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.showNotes()
    }


}