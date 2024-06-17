package com.c3dev.bagueton.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.AccountViewModel
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@Composable
fun LoginScreen(navHostController: NavHostController? = null,
                accountViewModel: AccountViewModel
) {

    Scaffold(
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ) { innerPadding ->


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.icone),
                contentDescription = "logo",
                Modifier
                    .width(200.dp)
                    .padding(bottom = 60.dp), contentScale = ContentScale.FillWidth
            )

            Column(
                Modifier.padding(start = 60.dp, end = 60.dp),
                horizontalAlignment = Alignment.End
            ) {

                // Champ pour le nom d'utilisateur
                OutlinedTextField(
                    value = accountViewModel.username.value,
                    onValueChange = { accountViewModel.username.value = it },
                    label = { stringResource(id = R.string.username) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Champ pour le mot de passe
                OutlinedTextField(
                    value = accountViewModel.password.value,
                    onValueChange = { accountViewModel.password.value = it },
                    label = { stringResource(id = R.string.mpd) },
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
                        if (accountViewModel.username.value.isNotBlank() && accountViewModel.password.value.isNotBlank()) {
                            // Supposons que la connexion est réussie
                        } else {
                            accountViewModel.errorMessage.value =
                                "Nom d'utilisateur ou mot de passe invalide"
                        }
                    }, Modifier.fillMaxWidth(1f)

                ) {
                    Text(stringResource(id = R.string.login))
                }

                // Affichage des messages d'erreur
                if (accountViewModel.errorMessage.value.isNotEmpty()) {
                    Text(text = "errorMessage.value", color = MaterialTheme.colorScheme.error)
                }
                Column(modifier = Modifier.fillMaxWidth()) {

                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Contact",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{
                            run { navHostController?.navigate("ContactsFormScreen") }
                        })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = stringResource(id = R.string.version))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = stringResource(id = R.string.confidentiality),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{
                            run { navHostController?.navigate("PrivacyPolicyScreen") }
                        })

                    Text(text = stringResource(id = R.string.test),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{
                            run { navHostController?.navigate("TestScreen") }
                        })
                    Text(text = stringResource(id = R.string.unboarding),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{
                            run { navHostController?.navigate("UnboardingScreen") }
                        })
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            LoginScreen(accountViewModel= AccountViewModel())
        }
    }
}

