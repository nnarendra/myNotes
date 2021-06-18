package com.test.mynotes.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

    @Insert
    suspend fun insert( notes:Note):Long

    @Update
    suspend fun update( notes: Note)

    @Delete
    suspend fun delete(notes: Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()

    @Query("SELECT * FROM Note")
     fun getAllNotes(): LiveData<List<Note>>

}