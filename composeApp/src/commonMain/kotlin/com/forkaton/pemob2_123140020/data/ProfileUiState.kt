package com.forkaton.pemob2_123140020.data

data class ProfileUiState(
    val name: String = "Anselmus Herpin Hasugian",
    val bio: String = "Halo! Saya adalah mahasiswa Program Studi Teknik Informatika di Institut Teknologi Sumatera dengan NIM 123140020. Saya memiliki minat mendalam di bidang rekayasa perangkat lunak, khususnya pengembangan aplikasi mobile menggunakan Kotlin Multiplatform dan Jetpack Compose. Saya merupakan individu yang teliti, sangat menyukai tantangan dalam memecahkan masalah (troubleshooting) kode, dan selalu berorientasi pada penyusunan arsitektur aplikasi yang bersih (Clean Code).",
    val isDarkMode: Boolean = false,
    val isEditing: Boolean = false
)