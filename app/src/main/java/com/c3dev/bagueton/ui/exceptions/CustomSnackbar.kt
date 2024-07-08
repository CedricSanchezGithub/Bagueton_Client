package com.c3dev.bagueton.ui.exceptions

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CustomSnackbar(snackbarHostState: SnackbarHostState, customSnackBarViewModel: CustomSnackBarViewModel) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                action = {
                    data.visuals.actionLabel?.let { actionLabel ->
                        TextButton(onClick = { data.dismiss() }) {
                            Text(text = actionLabel)
                        }
                    }
                }
            ) {
                Text(text = data.visuals.message)
            }
        },
        modifier = Modifier.padding(16.dp)
    )

    // Utilisez LaunchedEffect pour écouter les changements de errorServer
    LaunchedEffect(customSnackBarViewModel.errorServer.value) {
        if (customSnackBarViewModel.errorServer.value) {
            snackbarHostState.showSnackbar(
                message = customSnackBarViewModel.errorServerMessage.value,
                actionLabel = "Fermer"
            )
            customSnackBarViewModel.errorServer.value = false // Réinitialisez l'état après affichage
        }
    }
}
