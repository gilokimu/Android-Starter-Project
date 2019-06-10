package me.gilo.localdataprovider.db


import androidx.room.Database
import androidx.room.RoomDatabase
import me.gilo.localdataprovider.dao.DaoAccess
import me.gilo.localdataprovider.models.NoteEntity


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun daoAccess(): DaoAccess
}
