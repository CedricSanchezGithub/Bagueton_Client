package com.example.bagueton_v1.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp


@Composable
fun MyBottomAppBar() {
    BottomAppBar(
        // Personnalisez l'apparence de votre BottomAppBar ici
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,

    ) {
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Build, contentDescription = "Accueil",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoris",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Paramètres",
                Modifier.size(40.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyBottomAppBar() {
    MyBottomAppBar()
}
