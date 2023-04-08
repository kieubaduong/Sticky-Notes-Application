package com.example.stickynotesapplication.features.note.domain.use_case

data class NoteUseCase(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
)
