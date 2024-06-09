package com.example.bagueton_v1.ui.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.CalendarEvent
import com.example.bagueton_v1.ui.CalendarViewModel
import com.example.bagueton_v1.ui.ui.MyBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(calendarViewModel: CalendarViewModel, navHostController: NavHostController) {
    var selectedDay by remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var eventText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Calendar") })
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(31) { day ->
                    DayItem(
                        day = day + 1,
                        events = calendarViewModel.getEventsForDay(day + 1),
                        onClick = {
                            selectedDay = day + 1
                            showDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add Event") },
            text = {
                Column {
                    TextField(
                        value = eventText,
                        onValueChange = { eventText = it },
                        placeholder = { Text("Event Description") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    selectedDay?.let {
                        calendarViewModel.addEvent(it, eventText.text)
                        eventText = TextFieldValue("")
                        showDialog = false
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun DayItem(day: Int, events: List<CalendarEvent>, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text("Day $day", style = MaterialTheme.typography.bodyMedium)
        events.forEach { event ->
            Text("- ${event.event}", style = MaterialTheme.typography.titleMedium)
        }
    }
}
