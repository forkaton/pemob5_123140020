package com.forkaton.pemob2_123140020

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.forkaton.pemob2_123140020.screens.MainAppScreen
import com.forkaton.pemob2_123140020.viewmodel.ProfileViewModel

@Composable
fun App() {
    // 1. Inisialisasi ViewModel Profil di puncak aplikasi
    val profileViewModel: ProfileViewModel = viewModel()
    val uiState by profileViewModel.uiState.collectAsState()

    // 2. Terapkan logika tema
    MaterialTheme(
        colorScheme = if (uiState.isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            // 3. Operkan ViewModel ke Navigasi Utama
            MainAppScreen(profileViewModel = profileViewModel)
        }
    }
}