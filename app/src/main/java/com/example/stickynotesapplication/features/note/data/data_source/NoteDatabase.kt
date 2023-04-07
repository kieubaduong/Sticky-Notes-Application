package com.example.stickynotesapplication.features.note.data.data_source

import androidx.room.Database
import com.example.stickynotesapplication.features.note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase {
}