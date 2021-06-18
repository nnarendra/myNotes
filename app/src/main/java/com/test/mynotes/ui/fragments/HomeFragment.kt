package com.test.mynotes.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.mynotes.R
import com.test.mynotes.databinding.FragmentHomeBinding
import com.test.mynotes.db.Note
import com.test.mynotes.db.NoteDataBase
import com.test.mynotes.intrfaces.ClickListener
import com.test.mynotes.ui.adapters.NoteListAdapter
import com.test.mynotes.utils.NoteViewModelFactory
import com.test.mynotes.viewmodels.NoteViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment(), ClickListener {

    private var viewModel: NoteViewModel? = null
    private var binding: FragmentHomeBinding? = null
    private lateinit var listener: ClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete_all -> {
                deleteAllNotes()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllNotes() {
        launch {
            viewModel?.deleteAllNotes()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu?.clear()
        inflater?.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        this.setHasOptionsMenu(true)
        return binding?.root
        // return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener=this
        activity?.let {
            viewModel = ViewModelProvider(this, NoteViewModelFactory(NoteDataBase.invoke(it))).get(
                NoteViewModel::class.java
            )
        }
        viewModel?.liveDataNotesList?.observe(viewLifecycleOwner,Observer{
            var list: List<Note> = it
           binding?.rvNotes?.layoutManager= LinearLayoutManager(activity)
            binding?.rvNotes?.adapter=NoteListAdapter(list,listener)

        })

        binding?.btnAddNotes?.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view, AddNoteFragment())
                ?.commitAllowingStateLoss()
            //viewModel?.insertNotes(Note("Note 1", "test", false, 1, "", ""))
        }




    }

    override fun onDeleteClick(note: Note) {
        viewModel?.deleteNote(note)
    }

    override fun onLikeClick(note: Note) {
        viewModel?.update(note)
    }
}