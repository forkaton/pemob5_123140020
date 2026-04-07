package com.forkaton.pemob2_123140020.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Model Data Catatan
data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val isFavorite: Boolean = false
)

class NotesViewModel : ViewModel() {
    // Data dummy awal agar aplikasi tidak kosong
    private val _notes = MutableStateFlow(listOf(
        Note(1, "Belajar Jetpack Compose", "Mempelajari State, Recomposition, dan Navigation Compose untuk Tugas Praktikum 5.", true),
        Note(2, "Ide Proyek Akhir", "Membuat aplikasi manajemen keuangan dengan Kotlin Multiplatform.", false)
    ))
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    // Fungsi Tambah Catatan Baru
    fun addNote(title: String, content: String) {
        val newId = (_notes.value.maxOfOrNull { it.id } ?: 0) + 1
        val newNote = Note(id = newId, title = title, content = content)
        _notes.value = _notes.value + newNote
    }

    // Fungsi Mengubah Status Favorit
    fun toggleFavorite(id: Int) {
        _notes.value = _notes.value.map { note ->
            if (note.id == id) note.copy(isFavorite = !note.isFavorite) else note
        }
    }

    // Fungsi Mengambil 1 Catatan Spesifik untuk Layar Detail
    fun getNoteById(id: Int): Note? {
        return _notes.value.find { it.id == id }
    }
    // Fungsi Menyimpan Perubahan Edit Catatan
    fun updateNote(id: Int, newTitle: String, newContent: String) {
        _notes.value = _notes.value.map { note ->
            if (note.id == id) note.copy(title = newTitle, content = newContent) else note
        }
    }

}