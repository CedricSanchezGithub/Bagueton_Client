import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.MyBottomAppBar

@Composable
fun ContactsFormScreen(navHostController: NavHostController? = null, contactsViewModel: ContactsViewModel = ContactsViewModel()) {

    Scaffold(
        bottomBar = {
            MyBottomAppBar(navHostController = navHostController)
        }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp)
        ) {
            if (contactsViewModel.isSubmitted.value) {
                Text(text = "Merci de nous avoir contacté !", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = "Une idée ? Un problème ?", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Contactez nous !")

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = contactsViewModel.name.value,
                    onValueChange = { contactsViewModel.name.value = it },
                    label = { Text("Entrez votre nom") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactsViewModel.email.value,
                    onValueChange = { contactsViewModel.email.value = it },
                    label = { Text("Entrez votre email") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactsViewModel.message.value,
                    onValueChange = { contactsViewModel.message.value = it },
                    label = { Text("Entrez votre message") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { contactsViewModel.createForm(name = contactsViewModel.name.value, email = contactsViewModel.email.value, message = contactsViewModel.message.value) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ContactsFormScreenPreview() {
    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ContactsFormScreen()
        }
    }
}
