package me.gilo.starter.di

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import me.gilo.core.repositories.NoteRepository
import me.gilo.localdataprovider.repository.RoomNoteRepository

import javax.inject.Named
import javax.inject.Singleton


@Module
internal class RepositoryModule {

    @Provides
    @Singleton
    internal fun providesNoteRepository(context: Context) : NoteRepository = RoomNoteRepository(context)


}
