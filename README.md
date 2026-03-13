# Tugas Praktikum - Pemrograman Mobile

**Nama:** Anselmus Herpin Hasugian  
**NIM:** 123140020  

## Screenshot Aplikasi

| Android | Desktop |
|---------|---------|
| ![Android Screenshot](placeholder_android.png) | ![Desktop Screenshot](placeholder_desktop.png) |

> **Catatan:** Silakan ganti file `placeholder_android.png` dan `placeholder_desktop.png` dengan screenshot asli aplikasi Anda.

## Deskripsi Proyek
Aplikasi ini dikembangkan menggunakan **Kotlin Multiplatform** dan **Compose Multiplatform**. Fitur utama meliputi:
- **Layouting:** Implementasi Column, Row, dan Box.
- **Reusable Composables:** Header profil, Card biografi, dan Item info kontak yang modular.
- **UI Interaction:** Tombol interaktif dengan animasi (AnimatedVisibility) untuk menampilkan informasi kontak.
- **Styling:** Penggunaan Modifiers untuk shadow, clipping, dan responsive design.

## Cara Menjalankan
### Android
```shell
.\gradlew.bat :composeApp:assembleDebug
```
### Desktop
```shell
.\gradlew.bat :composeApp:run
```
