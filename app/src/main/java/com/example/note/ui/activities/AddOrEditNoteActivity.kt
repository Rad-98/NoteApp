package com.example.note.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.note.database.entity.NoteEntity
import com.example.note.databinding.ActivityAddEditNoteBinding
import com.example.note.viewmodels.AddOrEditNoteViewModel

class AddOrEditNoteActivity() : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddEditNoteBinding
    private val addOrEditNoteViewModel: AddOrEditNoteViewModel by lazy {
        ViewModelProvider(this).get(AddOrEditNoteViewModel::class.java)
    }
    private var id: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener(this)
        binding.btnEdit.setOnClickListener(this)

        addOrEditNoteViewModel.isOk.observe(this) {
            if (it) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }

        val inputIntent = intent
        id = inputIntent.getIntExtra("ID", 0)
        binding.txtTitle.setText(inputIntent.getStringExtra("TITLE"))
        binding.txtDesc.setText(inputIntent.getStringExtra("DESC"))

        val btn = inputIntent.getStringExtra("EDIT_NOTE")
        if (btn == "edit note") {
            binding.btnEdit.visibility = View.VISIBLE
            binding.btnSave.visibility = View.GONE
        } else {
            binding.btnSave.visibility = View.VISIBLE
            binding.btnEdit.visibility = View.GONE

        }


    }


    override fun onClick(v: View?) {
        val title = binding.txtTitle.text.toString()

        when (v) {
            binding.btnEdit ->
                if (title != ""){
                    addOrEditNoteViewModel.editNote(
                        NoteEntity(
                            id = id!!,
                            title = binding.txtTitle.text.toString(),
                            description = binding.txtDesc.text.toString()
                        )
                    )
                }else{
                    Toast.makeText(this, "Please set Title...", Toast.LENGTH_SHORT).show()
                }

            binding.btnSave ->
                if (title != ""){
                addOrEditNoteViewModel.addNote(
                    NoteEntity(
                        title = binding.txtTitle.text.toString(),
                        description = binding.txtDesc.text.toString()
                    )
                )
        }else{
            Toast.makeText(this, "Please set Title...", Toast.LENGTH_SHORT).show()
        }
        }
    }
}