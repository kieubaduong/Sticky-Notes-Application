package com.example.stickynotesapplication.features.note.domain.use_case

import com.example.stickynotesapplication.features.note.domain.model.Note
import com.example.stickynotesapplication.features.note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
}