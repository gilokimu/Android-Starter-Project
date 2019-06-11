package me.gilo.localdataprovider.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import me.gilo.localdataprovider.models.NoteEntity


@Dao
interface DaoAccess {

    @Insert
    fun insertTask(noteEntity: NoteEntity): Long?


    @Query("SELECT * FROM NoteEntity ORDER BY created_at desc")
    fun fetchAllTasks(): LiveData<List<NoteEntity>>


    @Query("SELECT * FROM NoteEntity WHERE id =:taskId")
    fun getTask(taskId: Int): LiveData<NoteEntity>


    @Update
    fun updateTask(noteEntity: NoteEntity)


    @Delete
    fun deleteTask(noteEntity: NoteEntity)
}
