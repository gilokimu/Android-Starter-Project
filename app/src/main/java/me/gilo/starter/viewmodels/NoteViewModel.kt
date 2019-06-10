package me.gilo.starter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import me.gilo.core.entity.Note
import me.gilo.core.repositories.NoteRepository
import me.gilo.localdataprovider.repository.RoomNoteRepository
import me.gilo.starter.common.CallBackLiveData
import me.gilo.starter.common.CompletionGenericLiveData
import me.gilo.starter.common.CompletionLiveData
import me.gilo.starter.common.DocumentLiveData
import me.gilo.starter.data.response.ApiResponse
import me.gilo.starter.data.response.LoginResponse
import me.gilo.starter.models.User
import me.gilo.starter.repo.AppNoteRepository
import me.gilo.starter.repo.FirebaseRepository
import me.gilo.starter.repo.UserRepository

import javax.inject.Inject


class NoteViewModel @Inject
internal constructor(private val noteRepository: AppNoteRepository) : ViewModel() {

    fun add(note: Note) {
        noteRepository.add(note)
    }

    fun update(note: Note) {
        noteRepository.update(note)
    }

    fun delete(id: Int) {
        noteRepository.delete(id)
    }

    fun delete(note: Note) {
        noteRepository.delete(note)
    }

    fun note(id: Int): Note = noteRepository.note(id)

    fun notes(): List<Note> = noteRepository.notes()

}