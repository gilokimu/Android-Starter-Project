package me.gilo.localdataprovider.tasks

import android.os.AsyncTask
import android.provider.ContactsContract
import me.gilo.core.entity.Note
import me.gilo.localdataprovider.db.NoteDatabase
import me.gilo.localdataprovider.models.toNoteEntity


class AsyncExecutor : AsyncTask<Any?, Any?, Any?>() {
    override fun doInBackground(vararg params: Any?): Any? {
        return null
    }

}


class InsertNote(private val noteDatabase: NoteDatabase, val note : Note) : AsyncTask<Void, Void, Long?>() {

    override fun doInBackground(vararg voids: Void): Long? {
        return noteDatabase.daoAccess().insertTask(note.toNoteEntity())
    }
}

class UpdateNote(private val noteDatabase: NoteDatabase, val note : Note) : AsyncTask<Void, Void, Unit>() {

    override fun doInBackground(vararg voids: Void) {
        return noteDatabase.daoAccess().updateTask(note.toNoteEntity())
    }
}

class DeleteNote(private val noteDatabase: NoteDatabase, val note : Note) : AsyncTask<Void, Void, Unit>() {

    override fun doInBackground(vararg voids: Void) {
        return noteDatabase.daoAccess().deleteTask(note.toNoteEntity())
    }
}
