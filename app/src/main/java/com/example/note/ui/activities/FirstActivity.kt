package com.example.note.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.note.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.exit.setOnClickListener(this)
        binding.goToAllNotes.setOnClickListener(this)
        binding.goToAddNote.setOnClickListener(this)
        binding.goToAoi.setOnClickListener(this)
        binding.other.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v) {
            binding.exit ->
                showBasicDialog(v)
            binding.goToAllNotes ->
                startActivity(Intent(this, ShowNotesActivity::class.java))
            binding.goToAddNote ->
                startActivity(Intent(this, AddOrEditNoteActivity::class.java))
            binding.goToAoi ->
                startActivity(Intent(this, QuoteActivity::class.java))
            binding.other ->
                startActivity(Intent(this, NewsActivity::class.java))
        }
    }


    fun showBasicDialog(view: View?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Exit") { _, _ ->
            finish()
        }
        builder.setNegativeButton("Cancel") { _, _ ->
            return@setNegativeButton
        }
        builder.show()
    }
}