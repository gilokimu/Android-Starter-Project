package me.gilo.starter.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import me.gilo.starter.dao.DaoAccess;
import me.gilo.starter.models.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
