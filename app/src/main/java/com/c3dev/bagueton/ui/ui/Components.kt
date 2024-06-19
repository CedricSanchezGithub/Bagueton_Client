package com.c3dev.bagueton.ui.ui

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    baguetonViewModel: com.c3dev.bagueton.ui.BaguetonViewModel,
    welcomeMessage: String? = stringResource(id = R.string.welcome_message),
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
                    .heightIn(min = 10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .border(1.dp, MaterialTheme.colorScheme.inverseOnSurface, RoundedCornerShape(5.dp))
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
fun ConfirmDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Confirmer la suppression") },
        text = { Text("Êtes-vous sûr de vouloir supprimer cette recette ?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirmer")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Annuler")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewMyBottomAppBar() {
    MyBottomAppBar()
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchBarPreview() {
    SearchBar(baguetonViewModel = com.c3dev.bagueton.ui.BaguetonViewModel())
}
