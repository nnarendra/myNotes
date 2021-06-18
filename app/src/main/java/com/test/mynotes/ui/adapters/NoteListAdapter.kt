package com.test.mynotes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.mynotes.databinding.ListItemNoteBinding
import com.test.mynotes.db.Note
import com.test.mynotes.intrfaces.ClickListener
import com.test.mynotes.ui.viewholders.NoteViewHolder

class NoteListAdapter(private val noteList: List<Note>, private val clickListener: ClickListener) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ListItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItems(noteList[position],clickListener)
    }

    override fun getItemCount(): Int = noteList.size
}