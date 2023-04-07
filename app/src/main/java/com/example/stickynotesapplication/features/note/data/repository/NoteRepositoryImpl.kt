package com.example.stickynotesapplication.features.note.data.repository

import com.example.stickynotesapplication.features.note.data.data_source.NoteDao
import com.example.stickynotesapplication.features.note.domain.model.Note
import com.example.stickynotesapplication.features.note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) :NoteRepository{
    override fun getAllNotes() = noteDao.getAllNotes()
    override suspend fun getNoteById(id: Int) = noteDao.getNoteById(id)
    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
}