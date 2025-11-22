package com.example.senaapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Main Navigation Host
@Composable
fun MainAppScreen() {
    var selectedItemIndex by remember { mutableStateOf(2) } // 2 is the index for Home

    Scaffold(
        bottomBar = {
            AppBottomNavigation(selectedItemIndex = selectedItemIndex) {
                selectedItemIndex = it
            }
        },
        containerColor = Color(0xFF78d5fb) // Set the background color here
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItemIndex) {
                0 -> CoursesScreen()
                1 -> DictionaryScreen()
                2 -> HomeContent()
                3 -> ProfileScreen()
                4 -> SettingsScreen()
            }
        }
    }
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingSection(name = "Mario")
        Spacer(modifier = Modifier.height(24.dp))
        StreakAndProgressSection()
        Spacer(modifier = Modifier.height(16.dp))
        CurrentCourseSection(
            courseName = "Lenguaje de Señas Básico",
            onContinue = { /*TODO*/ },
            onRestart = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DailyQuizSection(
            quizName = "Quiz de Saludos",
            score = 85, // Use null to show the "Start" button
            onStart = { /*TODO*/ }
        )
    }
}

// Placeholder Screens
@Composable
fun CoursesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Cursos", fontSize = 32.sp, color = Color.White)
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Perfil", fontSize = 32.sp, color = Color.White)
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Ajustes", fontSize = 32.sp, color = Color.White)
    }
}


// --- Reusable Components from the original HomeScreen ---

@Composable
fun GreetingSection(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Hola, $name",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "¿Listo para aprender?",
            fontSize = 20.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun StreakAndProgressSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoCard(title = "Racha de días", value = "5")
        CircularProgressCard(title = "Progreso Total", progress = 0.45f)
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier.size(width = 150.dp, height = 100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF78d5fb))
        }
    }
}

@Composable
fun CircularProgressCard(title: String, progress: Float) {
    Card(
        modifier = Modifier.size(width = 150.dp, height = 100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(width = 120.dp, height = 60.dp)) {
                    // Background arc
                    drawArc(
                        color = Color.Gray.copy(alpha = 0.3f),
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                    )
                    // Progress arc
                    drawArc(
                        color = Color(0xFFFFB6C1),
                        startAngle = 180f,
                        sweepAngle = progress * 180f,
                        useCenter = false,
                        style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                Text(
                    text = "${(progress * 100).toInt()}%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF78d5fb)
                )
            }
        }
    }
}


@Composable
fun CurrentCourseSection(courseName: String, onContinue: () -> Unit, onRestart: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Curso actual", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = courseName, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { 0.6f },
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFFFB6C1),
                trackColor = Color.Gray.copy(alpha = 0.3f),
                strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = onContinue,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
                ) {
                    Text("Continuar")
                }
                OutlinedButton(
                    onClick = onRestart,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFFB6C1)),
                    border = BorderStroke(1.dp, Color(0xFFFFB6C1))
                ) {
                    Text("Volver a empezar")
                }
            }
        }
    }
}

@Composable
fun DailyQuizSection(quizName: String, score: Int?, onStart: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Quiz del día", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = quizName, fontSize = 16.sp)
            }
            if (score != null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Puntuación:", fontSize = 14.sp)
                    Text(
                        text = "$score/100",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF78d5fb)
                    )
                }
            } else {
                Button(
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
                ) {
                    Text("Empezar")
                }
            }
        }
    }
}


@Composable
fun AppBottomNavigation(selectedItemIndex: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf(
        "Cursos" to Icons.Default.Book,
        "Diccionario" to Icons.Default.MenuBook,
        "Inicio" to Icons.Default.Home,
        "Perfil" to Icons.Default.Person,
        "Ajustes" to Icons.Default.Settings
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = selectedItemIndex == index,
                selected = selectedItemIndex == index,
                onClick = { onItemSelected(index) },
                icon = { Icon(item.second, contentDescription = item.first) },
                label = { Text(item.first) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFB6C1),
                    selectedTextColor = Color(0xFFFFB6C1),
                    indicatorColor = Color(0xFFFFB6C1).copy(alpha = 0.1f),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppScreenPreview() {
    MainAppScreen()
}
