package com.test.mynotes.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.test.mynotes.R
import com.test.mynotes.databinding.ListItemNoteBinding
import com.test.mynotes.db.Note
import com.test.mynotes.intrfaces.ClickListener

class NoteViewHolder(private var itemViewBinding: ListItemNoteBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {
    fun bindItems(note: Note, clickListener: ClickListener) {
        itemViewBinding.tvNoteTittle.text = note.tittle
        itemViewBinding.tvNoteDescription.text = note.description
        if(note.liked_flag==true){
            itemViewBinding.ivLike.setImageResource(R.drawable.ic_liked_icon)
        }else{
            itemViewBinding.ivLike.setImageResource(R.drawable.ic_not_liked_icon)
        }

        itemViewBinding.ivDelete.setOnClickListener {
            clickListener.onDeleteClick(note)
        }
        itemViewBinding.ivLike.setOnClickListener {
            note.liked_flag = note.liked_flag != true


            clickListener.onLikeClick(note)
        }
    }
}