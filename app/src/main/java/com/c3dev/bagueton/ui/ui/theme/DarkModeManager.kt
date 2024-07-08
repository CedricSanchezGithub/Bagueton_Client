package com.c3dev.bagueton.ui.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun ThemeToggleButton(themeViewModel: ThemeViewModel) {
    val isDarkTheme by themeViewModel.isDarkTheme

    IconButton(onClick = { themeViewModel.isDarkTheme.value = !isDarkTheme }) {
        Icon(
            imageVector = if (isDarkTheme) Icons.Default.Brightness7 else Icons.Default.Brightness2,
            contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode"
        )
    }
    if (isDarkTheme) {
        println("Dark Theme")
    } else {
        println("Light Theme")
    }
}
