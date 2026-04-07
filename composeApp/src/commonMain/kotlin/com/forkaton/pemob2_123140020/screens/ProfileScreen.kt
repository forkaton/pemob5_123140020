package com.forkaton.pemob2_123140020.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// --- IMPORT RESOURCE YANG BENAR ---
import org.jetbrains.compose.resources.painterResource
import com.forkaton.pemob2_123140020.Res
import com.forkaton.pemob2_123140020.foto_ansel
import com.forkaton.pemob2_123140020.ui.LabeledTextField
import com.forkaton.pemob2_123140020.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showContactInfo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Fitur Dark Mode Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dark Mode", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = uiState.isDarkMode, onCheckedChange = { viewModel.toggleDarkMode(it) })
        }

        ProfileHeader(
            name = uiState.name,
            role = "Mahasiswa Teknik Informatika - ITERA"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Animasi Transisi Form Edit dan Tampilan Biodata
            Crossfade(targetState = uiState.isEditing) { isEditing ->
                if (isEditing) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Edit Profile",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            LabeledTextField(label = "Nama", value = uiState.name, onValueChange = { viewModel.updateName(it) })
                            LabeledTextField(label = "Bio", value = uiState.bio, onValueChange = { viewModel.updateBio(it) })
                            Button(
                                onClick = { viewModel.toggleEditMode() },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Simpan")
                            }
                        }
                    }
                } else {
                    ProfileCard(bio = uiState.bio, onEditClick = { viewModel.toggleEditMode() })
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { showContactInfo = !showContactInfo },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(if (showContactInfo) "Sembunyikan Kontak" else "Hubungi Saya")
            }

            AnimatedVisibility(
                visible = showContactInfo,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoItem(icon = Icons.Default.Email, text = "anselmus.123140020@student.itera.ac.id")
                    InfoItem(icon = Icons.Default.Phone, text = "072170918455")
                    InfoItem(icon = Icons.Default.LocationOn, text = "Way Hui, Lampung")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProfileHeader(name: String, role: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.primary)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Surface(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shadowElevation = 8.dp
            ) {
                // PEMANGGILAN GAMBAR YANG BENAR
                Image(
                    painter = painterResource(Res.drawable.foto_ansel),
                    contentDescription = "Foto Profil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = role,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProfileCard(bio: String, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Biodata Saya",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                TextButton(onClick = onEditClick) {
                    Text("Edit")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bio,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                lineHeight = 22.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun InfoItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}