package com.test.mynotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mynotes.db.Note
import com.test.mynotes.db.NoteDataBase
import com.test.mynotes.utils.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(dbInstance: NoteDataBase) : ViewModel() {
    private var repository: NoteRepository = NoteRepository(dbInstance).also { repository = it }

    var liveDataNotesList = repository.noteList

    /*init {
        NoteRepository(dbInstance).also { repository = it }
    }*/

     fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.deleteAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.delete(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.update(note)
        }
    }

    suspend fun insertNotes(note: Note): Boolean {

        return withContext(viewModelScope.coroutineContext + Dispatchers.Main) {
            val status: Long = repository.insertNote(note)
            val b = status != -1L
            b
        }

    }

}