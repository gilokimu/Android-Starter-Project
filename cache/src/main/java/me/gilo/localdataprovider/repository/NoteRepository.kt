package me.gilo.localdataprovider.repository


import android.content.Context
import android.os.AsyncTask
import android.os.AsyncTask.execute
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import androidx.room.Room
import me.gilo.core.entity.Note
import me.gilo.core.repositories.NoteRepository
import me.gilo.localdataprovider.db.NoteDatabase
import me.gilo.localdataprovider.models.NoteEntity
import me.gilo.localdataprovider.models.toNote
import me.gilo.localdataprovider.models.toNoteEntity
import me.gilo.localdataprovider.tasks.AsyncExecutor
import me.gilo.localdataprovider.tasks.DeleteNote
import me.gilo.localdataprovider.tasks.InsertNote
import me.gilo.localdataprovider.tasks.UpdateNote
import me.gilo.localdataprovider.utils.AppUtils

class RoomNoteRepository: NoteRepository{

    private val dbName = "starter_db"
    private val noteDatabase: NoteDatabase

    constructor(context: Context) {
        noteDatabase = Room.databaseBuilder(context, NoteDatabase::class.java, dbName).build()
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
        InsertNote(noteDatabase, note).execute()
    }

    override fun update(note: Note) {
        note.modifiedAt = AppUtils.currentDateTime
        UpdateNote(noteDatabase, note).execute()

    }

    override fun delete(note: Note) {
        DeleteNote(noteDatabase, note).execute()
    }

    override fun note(id: Int): LiveData<Note> {
        return Transformations.map(noteDatabase.daoAccess().getTask(id)){
            noteEntity: NoteEntity? -> noteEntity?.toNote()
        }
    }

    override fun notes(): LiveData<List<Note>> {
        return Transformations.map(noteDatabase.daoAccess().fetchAllTasks()){
            noteEntities: List<NoteEntity>? ->  noteEntities?.map { noteEntity -> noteEntity.toNote() }
        }
    }
}

