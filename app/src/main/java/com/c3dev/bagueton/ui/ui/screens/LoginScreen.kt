import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.AccountViewModel
import com.c3dev.bagueton.ui.model.usermanager.User
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@Composable
fun LoginScreen(accountViewModel: AccountViewModel,
                navHostController: NavHostController? = null) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isRegisterScreen by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mot de passe") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (isRegisterScreen) {
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmez votre mot de passe") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Button(
                onClick = {
                    if (isRegisterScreen) {
                        if (password == confirmPassword) {
                            accountViewModel.registerUser(User(username, password)) {
                                message = it
                            }
                        } else {
                            message = "Les mots de passe ne correspondent pas"
                        }
                    } else {
                        accountViewModel.loginUser(username, password) {
                            message = it
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(if (isRegisterScreen) "S'enregistrer" else "Login")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isRegisterScreen) "Déjà un compte ? Connectez-vous" else "Pas de compte ? S'enregistrer",
                modifier = Modifier
                    .clickable { isRegisterScreen = !isRegisterScreen }
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = message, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Contact",
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navHostController?.navigate("ContactsFormScreen")
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.version))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.confidentiality),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navHostController?.navigate("PrivacyPolicyScreen")
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.test),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navHostController?.navigate("TestScreen")
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.unboarding),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navHostController?.navigate("UnboardingScreen")
                    }
                )
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
            LoginScreen(accountViewModel = AccountViewModel())
        }
    }
}