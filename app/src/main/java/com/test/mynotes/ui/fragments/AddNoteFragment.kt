package com.test.mynotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.test.mynotes.R
import com.test.mynotes.databinding.FragmentAddNoteBinding
import com.test.mynotes.db.Note
import com.test.mynotes.db.NoteDataBase
import com.test.mynotes.utils.NoteViewModelFactory
import com.test.mynotes.viewmodels.NoteViewModel
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private var viewModel: NoteViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(this, NoteViewModelFactory(NoteDataBase.invoke(it))).get(
                NoteViewModel::class.java
            )
        }
        binding.btnSave.setOnClickListener {

            launch {
                var tittle: String = binding.etNotesTittle.text.toString()
                var description: String = binding.etNotesDescription.text.toString()
                if (tittle.isNotBlank()
                    && description.isNotBlank()
                ) {
                    val insertStatus =
                        viewModel?.insertNotes(Note(tittle, description, false, 1, "", ""))
                    if (insertStatus == true) {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.fragment_container_view, HomeFragment())
                            ?.commitAllowingStateLoss()
                    } else {
                        Toast.makeText(
                            activity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        activity,
                        "Please add tittle and description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }

    }

}