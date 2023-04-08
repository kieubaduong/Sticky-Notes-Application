package com.example.stickynotesapplication.features.note.domain.use_case

import com.example.stickynotesapplication.features.note.domain.model.Note
import com.example.stickynotesapplication.features.note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int) : Note? = repository.getNoteById(id)
}