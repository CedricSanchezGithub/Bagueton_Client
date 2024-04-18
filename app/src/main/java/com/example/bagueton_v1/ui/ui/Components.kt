package com.example.bagueton_v1.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bagueton_v1.ui.BaguetonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun SearchBar(modifier: Modifier = Modifier, baguetonViewModel: BaguetonViewModel, welcomeMessage: String? = "Bienvenue, utilisateur"

) {
    Column(modifier = modifier) {
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
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
            )
        if (welcomeMessage != null) {
        Spacer(modifier = Modifier.height(16.dp))
            Text(text = welcomeMessage,
                fontWeight = FontWeight(800),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(horizontal = 16.dp)
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

        ) {
        IconButton(onClick = { navHostController?.navigate("HomeScreen") }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("ToolsScreen") }) {
            Icon(Icons.Filled.Build, contentDescription = "Outils",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("FavoriteScreen") }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoris",
                Modifier.size(40.dp))
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { navHostController?.navigate("LoginScreen")  }) {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Paramètres",
                Modifier.size(40.dp))
        }

    }
}
@Composable
fun PreviewInfos(){
    Row {
        IconButton(onClick = { /* Gérer le clic ici */ }) {
            Icon(Icons.Filled.Home, contentDescription = "Accueil",
                Modifier.size(40.dp))
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
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewInfosPreview() {
    PreviewInfos()
}
