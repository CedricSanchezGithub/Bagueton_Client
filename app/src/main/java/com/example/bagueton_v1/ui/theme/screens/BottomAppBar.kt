package com.example.bagueton_v1.ui.theme.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyBottomAppBar() {
    BottomAppBar(
        // Personnalisez l'apparence de votre BottomAppBar ici
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,

    ) {
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil")
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Build, contentDescription = "Accueil")
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoris")
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Paramètres")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyBottomAppBar() {
    MyBottomAppBar()
}
