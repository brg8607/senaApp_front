package com.example.senaapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Dummy data for categories
val categories = listOf(
    "Abecedario", "Saludos", "Familia", "Animales", "Comida", "Lugares", "Preguntas", "Verbos"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Buscar en todo el diccionario...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xFFFFB6C1),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryCard(categoryName = category) { /* TODO: Navigate to category detail */ }
            }
        }
    }
}

@Composable
fun CategoryCard(categoryName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.aspectRatio(1.2f),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Text(
                text = categoryName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF78d5fb)
            )
        }
    }
}
