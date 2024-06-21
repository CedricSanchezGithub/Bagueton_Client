package com.c3dev.bagueton.ui.ui.screens.tests

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme
import com.c3dev.bagueton.ui.ui.theme.ThemeViewModel

@Composable
fun TestScreen(
    navHostController: NavHostController? = null,
    themeViewModel: ThemeViewModel
) {
    val isDarkTheme by themeViewModel.isDarkTheme

    Bagueton_v1Theme(useDarkTheme = isDarkTheme) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(text = "Test Screen", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { themeViewModel.isDarkTheme.value = !isDarkTheme }) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.Brightness7 else Icons.Default.Brightness2,
                            contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode"
                        )
                    }
                }
            },
            bottomBar = {
                MyBottomAppBar(navHostController = navHostController)
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Row {
                    Text(text = "blabla")
                }
                Image(
                    painter = rememberAsyncImagePainter("http://localhost:8082/images/baguette.png"),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TestScreenPreview() {
    val themeViewModel = ThemeViewModel().apply { isDarkTheme.value = false } // For preview
    Bagueton_v1Theme(useDarkTheme = themeViewModel.isDarkTheme.value) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            TestScreen(themeViewModel = themeViewModel)
        }
    }
}
