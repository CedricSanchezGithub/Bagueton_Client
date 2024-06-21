package com.c3dev.bagueton.ui.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    var isDarkTheme = mutableStateOf(false)
}

val LocalThemeViewModel = compositionLocalOf<ThemeViewModel> { error("No ThemeViewModel provided") }
