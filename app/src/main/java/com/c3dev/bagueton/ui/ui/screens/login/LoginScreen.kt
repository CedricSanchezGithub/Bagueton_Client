import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.model.beans.User
import com.c3dev.bagueton.ui.model.usermanager.AccountViewModel
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.screens.login.LoginViewModel
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme
import com.c3dev.bagueton.ui.ui.theme.LocalThemeViewModel
import com.c3dev.bagueton.ui.ui.theme.ThemeToggleButton

@Composable
fun LoginScreen(accountViewModel: AccountViewModel,
                navHostController: NavHostController? = null,
                loginViewModel: LoginViewModel) {


    val themeViewModel = LocalThemeViewModel.current

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                ThemeToggleButton(themeViewModel = themeViewModel)
            }
        },
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            if(!themeViewModel.isDarkTheme.value){

                Image(
                    ImageBitmap.imageResource(id = R.drawable.logobagueton),
                    contentDescription = "logo", modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                )
            } else{
                Image(
                    ImageBitmap.imageResource(id = R.drawable.logobagueton),
                    contentScale = ContentScale.Crop,
                    contentDescription = "logo", modifier = Modifier
                        .width(200.dp)
                        .border(3.dp, MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = loginViewModel.username.value,
                    onValueChange = { loginViewModel.username.value = it },
                    label = { Text(stringResource(id = R.string.username_label)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = loginViewModel.password.value,
                    onValueChange = { loginViewModel.password.value = it },
                    label = { Text(stringResource(id = R.string.password_label)) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (loginViewModel.isRegisterScreen.value) {
                    TextField(
                        value = loginViewModel.confirmPassword.value,
                        onValueChange = { loginViewModel.confirmPassword.value = it },
                        label = { Text(stringResource(id = R.string.confirm_password_label)) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Button(
                onClick = {
                    if (loginViewModel.isRegisterScreen.value) {
                        if (loginViewModel.password.value == loginViewModel.confirmPassword.value) {
                            accountViewModel.registerUser(User(loginViewModel.username.value, loginViewModel.password.value)) {
                                loginViewModel.message.value = it
                            }
                        } else {
                            loginViewModel.message.value = "Les mots de passe ne correspondent pas"
                        }
                    } else {
                        accountViewModel.loginUser(loginViewModel.username.value, loginViewModel.password.value) {
                            loginViewModel.message.value = it
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(if (loginViewModel.isRegisterScreen.value) { stringResource(id = R.string.register_button) } else { stringResource(id = R.string.login_button) })
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (loginViewModel.isRegisterScreen.value) stringResource(id = R.string.already_have_account) else stringResource(R.string.no_account_register),
                modifier = Modifier
                    .clickable {
                        loginViewModel.isRegisterScreen.value =
                            !loginViewModel.isRegisterScreen.value
                    }
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = loginViewModel.message.value, style = MaterialTheme.typography.bodyLarge)

            CustomizedLinks(navHostController)
        }
    }
}



@Composable
fun CustomizedLinks(navHostController: NavHostController?) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Contact
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navHostController?.navigate("ContactsFormScreen") }
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Contact")
                    Text(
                        text = "Contact",
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Version
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { /* Version action */ }
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Version")
                    Text(
                        text = stringResource(id = R.string.version_label),
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Confidentialité
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navHostController?.navigate("PrivacyPolicyScreen") }
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Confidentialité")
                    Text(
                        text = stringResource(id = R.string.confidentiality_label),
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Test
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navHostController?.navigate("TestScreen") }
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Test")
                    Text(
                        text = stringResource(id = R.string.test_label),
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Home
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navHostController?.navigate("UnboardingScreen") }
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                    Text(
                        text = stringResource(id = R.string.back_to_home_button),
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(top = 4.dp)
                    )
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
            LoginScreen(accountViewModel = AccountViewModel(), loginViewModel = LoginViewModel())
        }
    }
}