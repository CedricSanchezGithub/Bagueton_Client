package com.example.bagueton_v1.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


class CalendarViewModel : ViewModel() {
    val events = mutableStateListOf<CalendarEvent>()

    fun addEvent(day: Int, event: String) {
        events.add(CalendarEvent(day, event))
    }

    fun getEventsForDay(day: Int): List<CalendarEvent> {
        return events.filter { it.day == day }
    }
}

data class CalendarEvent(
    val day: Int,
    val event: String
)
