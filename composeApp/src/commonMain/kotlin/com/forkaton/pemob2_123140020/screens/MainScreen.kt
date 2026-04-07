package com.forkaton.pemob2_123140020.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.forkaton.pemob2_123140020.components.AppBottomNavigationBar
import com.forkaton.pemob2_123140020.navigation.BottomNavItem
import com.forkaton.pemob2_123140020.navigation.Screen
import com.forkaton.pemob2_123140020.viewmodel.NotesViewModel
import com.forkaton.pemob2_123140020.viewmodel.ProfileViewModel

@Composable
fun MainAppScreen(profileViewModel: ProfileViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Inisialisasi NotesViewModel
    val notesViewModel: NotesViewModel = viewModel()

    val showBottomBar = currentRoute in listOf(
        BottomNavItem.Notes.route, BottomNavItem.Favorites.route, BottomNavItem.Profile.route
    )

    Scaffold(
        bottomBar = { if (showBottomBar) AppBottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Notes.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // TABS
            composable(BottomNavItem.Notes.route) {
                NoteListScreen(
                    viewModel = notesViewModel,
                    onNoteClick = { noteId -> navController.navigate(Screen.NoteDetail.createRoute(noteId)) },
                    onAddClick = { navController.navigate(Screen.AddNote.route) }
                )
            }
            composable(BottomNavItem.Favorites.route) {
                FavoritesScreen(
                    viewModel = notesViewModel,
                    onNoteClick = { noteId -> navController.navigate(Screen.NoteDetail.createRoute(noteId)) }
                )
            }
            composable(BottomNavItem.Profile.route) {
                // Menampilkan Profile dari Tugas 4
                ProfileScreen(viewModel = profileViewModel)
            }

            // DETAILS
            composable(Screen.AddNote.route) {
                AddNoteScreen(viewModel = notesViewModel, onBack = { navController.popBackStack() })
            }
            
            // DETAIL
            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(
                    noteId = noteId, 
                    viewModel = notesViewModel, 
                    onBack = { navController.popBackStack() },
                    // Tangkap event onEditClick dan arahkan ke layar Edit membawa ID
                    onEditClick = { id -> navController.navigate(Screen.EditNote.createRoute(id)) }
                )
            }

            // EDIT
            composable(
                route = Screen.EditNote.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId, 
                    viewModel = notesViewModel, 
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}