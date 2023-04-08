package com.example.stickynotesapplication.features.note.domain.use_case

import com.example.stickynotesapplication.features.note.domain.model.InvalidNoteException
import com.example.stickynotesapplication.features.note.domain.model.Note
import com.example.stickynotesapplication.features.note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()){
            throw Exception("The title of the note can't be empty.")
        }
        if (note.content.isBlank()){
            throw Exception("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}