package me.gilo.starter.dao;


import androidx.lifecycle.LiveData;
import androidx.room.*;
import me.gilo.starter.models.Note;

import java.util.List;


@Dao
public interface DaoAccess {

    @Insert
    Long insertTask(Note note);


    @Query("SELECT * FROM Note ORDER BY created_at desc")
    LiveData<List<Note>> fetchAllTasks();


    @Query("SELECT * FROM Note WHERE id =:taskId")
    LiveData<Note> getTask(int taskId);


    @Update
    void updateTask(Note note);


    @Delete
    void deleteTask(Note note);
}
