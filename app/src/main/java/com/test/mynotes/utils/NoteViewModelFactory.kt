package com.test.mynotes.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mynotes.db.NoteDataBase
import com.test.mynotes.viewmodels.NoteViewModel

class NoteViewModelFactory(private val dbInstance: NoteDataBase):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(dbInstance) as T
        }
        throw IllegalArgumentException("Unknown viewModel")
    }
}