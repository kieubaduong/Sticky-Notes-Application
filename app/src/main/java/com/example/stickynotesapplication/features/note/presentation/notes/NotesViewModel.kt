package com.example.stickynotesapplication.features.note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stickynotesapplication.features.note.domain.model.Note
import com.example.stickynotesapplication.features.note.domain.use_case.NoteUseCase
import com.example.stickynotesapplication.features.note.domain.util.NoteOrder
import com.example.stickynotesapplication.features.note.domain.util.OrderType
import com.example.stickynotesapplication.features.note.presentation.notes.NotesEvent
import com.example.stickynotesapplication.features.note.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel(){

    private  val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob : Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                   noteUseCase.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !_state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            noteUseCase.getNotesUseCase().onEach { notes ->
                _state.value = _state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
        }
    }
}