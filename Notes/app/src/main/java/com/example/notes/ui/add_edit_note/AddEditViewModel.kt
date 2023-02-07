package com.example.notes.ui.add_edit_note

import androidx.lifecycle.ViewModel
import com.example.notes.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
     private val noteUseCase : NoteUseCase
) : ViewModel(){

}