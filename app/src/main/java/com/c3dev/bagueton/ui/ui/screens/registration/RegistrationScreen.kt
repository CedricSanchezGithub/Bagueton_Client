package com.c3dev.bagueton.ui.ui.screens.registration

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.model.usermanager.AccountViewModel
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@Composable
fun RegistrationScreen(accountViewModel: AccountViewModel) {




    // États pour stocker le nom d'utilisateur et le mot de passe entrés par l'utilisateur
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    // État pour gérer l'affichage des messages d'erreur
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(painter = painterResource(id = R.drawable.icone),
            contentDescription = "logo",
            Modifier
                .width(200.dp)
                .padding(bottom = 60.dp), contentScale = ContentScale.FillWidth )

        Column(Modifier.padding(start = 60.dp, end = 60.dp),
            horizontalAlignment = Alignment.End) {

            // Champ pour le nom d'utilisateur
            OutlinedTextField(
                value = accountViewModel.choosenUsername.value,
                onValueChange = { accountViewModel.choosenUsername.value = it },
                label = { Text(stringResource(id = R.string.username_choose)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )


            // Champ pour le mot de passe
            OutlinedTextField(
                value = accountViewModel.choosenPassword.value,
                onValueChange = {  accountViewModel.choosenPassword.value = it },
                label = { Text(stringResource(id = R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()

            )

            OutlinedTextField(
                value = accountViewModel.choosenPasswordAgain.value,
                onValueChange = {  accountViewModel.choosenPasswordAgain.value = it },
                label = { Text(stringResource(id = R.string.password_again)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))


            // Bouton de connexion
            Button(
                onClick = {
                    // Logique de validation ou de connexion
                    if (username.value.isNotBlank() && password.value.isNotBlank()) {
                        // Supposons que la connexion est réussie
                    } else {
                        errorMessage.value = "Nom d'utilisateur ou mot de passe invalide"
                    }
                },Modifier.fillMaxWidth(1f)

            ) {
                Text("S'inscrire")
            }

            // Affichage des messages d'erreur
            if (errorMessage.value.isNotEmpty()) {
                Text(text = "errorMessage.value", color = MaterialTheme.colorScheme.error)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){


            }
        }


        Spacer(modifier = Modifier.weight(1f))
        MyBottomAppBar()

    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegistrationScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RegistrationScreen(accountViewModel = AccountViewModel())
        }
    }
}

