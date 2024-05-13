package com.example.bagueton_v1.ui.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.BaguetonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    baguetonViewModel: BaguetonViewModel,
    welcomeMessage: String? = "Bienvenue, utilisateur",
    navHostController: NavHostController? = null
) {

    Column(modifier = modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Bouton retour
            IconButton(onClick = { navHostController?.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(Modifier.width(8.dp))

            // TextField pour la recherche
            TextField(
                value = baguetonViewModel.searchText.value,
                onValueChange = { baguetonViewModel.searchText.value = it },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (baguetonViewModel.searchText.value.isNotEmpty()) {
                        IconButton(onClick = {
                            baguetonViewModel.searchText.value = ""
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Effacer"
                            )
                        }
                    }
                },
                placeholder = { Text("Rechercher") },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 20.dp)
                    .clip(RoundedCornerShape(50))
                    .border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(50.dp))
            )

            Spacer(Modifier.width(8.dp))

            // Bouton pour plus d'options
            IconButton(onClick = { /* Déclenchez le menu ici */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Plus d'options",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        if (welcomeMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = welcomeMessage,
                fontWeight = FontWeight(800),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
    }


}


@Composable
fun MyBottomAppBar(navHostController: NavHostController? = null) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.height(50.dp)
        ) {
        IconButton(onClick = { navHostController?.navigate("HomeScreen") }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil",
                Modifier.size(30.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("ToolsScreen") }) {
            Icon(Icons.Filled.AutoGraph, contentDescription = "Outils",
                Modifier.size(30.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("ListRecipeScreen") }) {
            Icon(Icons.Filled.RestaurantMenu, contentDescription = "Menu",
                Modifier.size(30.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("LoginScreen")  }) {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Paramètres",
                Modifier.size(30.dp))
        }

    }
}


@Composable
fun ConfirmationSnackbar(snackbarHostState: SnackbarHostState, scope: CoroutineScope, message: String, baguetonViewModel: BaguetonViewModel) {
    SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(snackbarData = data,)
    }

    // Fonction pour montrer le Snackbar avec un message
    fun showSnackbar() {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "OK",
                duration = SnackbarDuration.Short,
            )
        }

    }
    // Cette fonction est appelée lorsqu'une recette est correctement envoyée au serveur
    showSnackbar()
    baguetonViewModel.snackBarValue.value = false
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewMyBottomAppBar() {
    MyBottomAppBar()
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchBarPreview() {
    SearchBar(baguetonViewModel = BaguetonViewModel())
}
