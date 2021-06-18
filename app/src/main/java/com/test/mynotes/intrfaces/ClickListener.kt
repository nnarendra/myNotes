package com.test.mynotes.intrfaces

import com.test.mynotes.db.Note

interface ClickListener {
    fun onDeleteClick( note: Note)
    fun onLikeClick(note:Note)
}