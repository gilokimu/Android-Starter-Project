package me.gilo.starter.repo

import android.content.Context
import me.gilo.core.entity.Note
import me.gilo.core.repositories.NoteRepository
import me.gilo.localdataprovider.repository.RoomNoteRepository
import javax.inject.Inject


class AppNoteRepository
@Inject constructor() : NoteRepository{


    @Inject
    lateinit var noteRepository : NoteRepository


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

    override fun note(id: Int) = noteRepository.note(id)

    override fun notes() = noteRepository.notes()
}
