package com.example.stickynotesapplication.features.note.domain.use_case

import com.example.stickynotesapplication.features.note.domain.model.Note
import com.example.stickynotesapplication.features.note.domain.repository.NoteRepository
import com.example.stickynotesapplication.features.note.domain.util.NoteOrder
import com.example.stickynotesapplication.features.note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)) : Flow<List<Note>> {
        return repository.getAllNotes()
    }
}