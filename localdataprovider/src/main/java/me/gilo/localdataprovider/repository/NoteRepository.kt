package me.gilo.localdataprovider.repository


import android.content.Context

import androidx.room.Room
import me.gilo.core.entity.Note
import me.gilo.core.repositories.NoteRepository
import me.gilo.localdataprovider.db.NoteDatabase
import me.gilo.localdataprovider.models.NoteEntity
import me.gilo.localdataprovider.models.toNote
import me.gilo.localdataprovider.models.toNoteEntity
import me.gilo.localdataprovider.tasks.AsyncExecutor
import me.gilo.localdataprovider.utils.AppUtils

class RoomNoteRepository(context: Context) : NoteRepository {


    private val DB_NAME = "db_task"
    private val noteDatabase: NoteDatabase

    init {
        noteDatabase = Room.databaseBuilder(context, NoteDatabase::class.java, DB_NAME).build()
    }

    @JvmOverloads
    fun add(title: String, description: String, encrypt: Boolean = false, password: String? = null) {
        val noteEntity = NoteEntity()
        noteEntity.title = title
        noteEntity.description = description
        noteEntity.createdAt = AppUtils.currentDateTime
        noteEntity.modifiedAt = AppUtils.currentDateTime
        noteEntity.isEncrypt = encrypt

        if (encrypt) {
            noteEntity.password = AppUtils.generateHash(password!!)
        } else
            noteEntity.password = null

        add(noteEntity.toNote())
    }

    override fun add(note: Note) {
        AsyncExecutor().execute(noteDatabase.daoAccess().insertTask(note.toNoteEntity()))
    }

    override fun update(note: Note) {
        note.modifiedAt = AppUtils.currentDateTime
        AsyncExecutor().execute(noteDatabase.daoAccess().updateTask(note.toNoteEntity()))

    }

    override fun delete(id: Int) {
        val task = note(id)
        AsyncExecutor().execute(noteDatabase.daoAccess().deleteTask(task.toNoteEntity()))
    }

    override fun delete(note: Note) {
        AsyncExecutor().execute(noteDatabase.daoAccess().deleteTask(note.toNoteEntity()))
    }

    override fun note(id: Int): Note {
        return noteDatabase.daoAccess().getTask(id).value!!.toNote()
    }

    override fun notes(): List<Note> {
        return noteDatabase.daoAccess().fetchAllTasks().value!!.map { noteEntity -> noteEntity.toNote() }
    }
}

