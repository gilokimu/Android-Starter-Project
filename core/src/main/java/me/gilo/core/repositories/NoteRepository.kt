package me.gilo.core.repositories

import me.gilo.core.entity.Note

interface NoteRepository{
    fun add(note: Note)
    fun update(note: Note)
    fun delete(id: Int)
    fun delete(note: Note)
    fun note(id: Int): Note?
    fun notes(): List<Note>?
}