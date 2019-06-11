package me.gilo.localdataprovider.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.gilo.localdataprovider.dao.DaoAccess
import me.gilo.localdataprovider.models.NoteEntity
import me.gilo.localdataprovider.utils.Converters


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun daoAccess(): DaoAccess
}
