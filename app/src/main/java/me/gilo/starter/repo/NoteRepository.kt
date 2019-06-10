package me.gilo.starter.repo

import android.content.Context
import me.gilo.core.entity.Note
import me.gilo.core.repositories.NoteRepository
import me.gilo.localdataprovider.repository.RoomNoteRepository


class AppNoteRepository(context: Context) : NoteRepository {

    private val noteRepository : NoteRepository

    init {
        noteRepository = RoomNoteRepository(context)
    }

    override fun add(note: Note) {
        noteRepository.add(note)
    }

    override fun update(note: Note) {
        noteRepository.update(note)
    }

    override fun delete(id: Int) {
        noteRepository.delete(id)
    }

    override fun delete(note: Note) {
        noteRepository.delete(note)
    }

    override fun note(id: Int): Note = noteRepository.note(id)

    override fun notes(): List<Note> = noteRepository.notes()
}
