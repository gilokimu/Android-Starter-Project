package me.gilo.starter.ui.note

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import me.gilo.starter.R

import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.content_add_note.*
import me.gilo.localdataprovider.tasks.AsyncExecutor
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

       AsyncExecutor().execute(viewModel.notes()?.map { note -> Log.d(note.title, note.description) })

    }

    private fun addNote() {
        var title = etTitle.text.toString()
        var description = etDescription.text.toString()

        viewModel.add(title, description)

        finish()
    }

}
