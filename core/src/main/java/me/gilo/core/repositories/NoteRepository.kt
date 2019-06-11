package me.gilo.core.repositories

import androidx.lifecycle.LiveData
import me.gilo.core.entity.Note

interface NoteRepository{
    fun add(note: Note)
    fun update(note: Note)
    fun delete(note: Note)
    fun note(id: Int): LiveData<Note>
    fun notes(): LiveData<List<Note>>
}