package com.test.mynotes.utils

import androidx.lifecycle.LiveData
import com.test.mynotes.db.Note
import com.test.mynotes.db.NoteDAO
import com.test.mynotes.db.NoteDataBase

class NoteRepository(dbInstance: NoteDataBase) {
    private var noteDAO: NoteDAO = dbInstance.getNotesDAO()
    val noteList:LiveData<List<Note>> = noteDAO.getAllNotes()



    public suspend fun insertNote( note: Note):Long{
      return noteDAO.insert(note)
    }

    public suspend fun deleteAllNotes(){
        noteDAO.deleteAll()

    }
    public suspend fun delete(note: Note){
        noteDAO.delete(note)
    }
    public suspend fun update(note: Note){
        noteDAO.update(note)
    }
}