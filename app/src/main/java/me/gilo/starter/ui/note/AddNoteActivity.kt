package me.gilo.starter.ui.note

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.content_add_note.*
import me.gilo.starter.R
import me.gilo.starter.ui.StarterActivity
import me.gilo.starter.viewmodels.NoteViewModel


class AddNoteActivity : StarterActivity<NoteViewModel>() {


    override lateinit var viewModel: NoteViewModel


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)

        viewModel = getViewModel(NoteViewModel::class.java)
        title = "Add Note"

        bAdd.setOnClickListener{addNote()}

       viewModel.notes().observe(this, Observer {
           notes -> notes.map { note -> Log.d(note.title, note.description) }
       })

    }

    private fun addNote() {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()

        viewModel.add(title, description)

        finish()
    }

}
