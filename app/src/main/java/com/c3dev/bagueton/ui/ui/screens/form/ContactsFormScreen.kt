package com.c3dev.bagueton.ui.ui.screens.form

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

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
                Text(text = "", style = MaterialTheme.typography.bodyLarge)
                Text(text = stringResource(id = R.string.thanks_message), style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = stringResource(id = R.string.contact_us), style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = contactsViewModel.name.value,
                    onValueChange = { contactsViewModel.name.value = it },
                    label = { Text(text = stringResource(id = R.string.name)) },
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactsViewModel.email.value,
                    onValueChange = { contactsViewModel.email.value = it },
                    label = { Text(text = stringResource(id = R.string.send_email)) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = contactsViewModel.message.value,
                    onValueChange = { contactsViewModel.message.value = it },
                    label = { Text(text = stringResource(id = R.string.message_to_send)) },
                    modifier = Modifier.height(200.dp)

                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { contactsViewModel.createForm(name = contactsViewModel.name.value, email = contactsViewModel.email.value, message = contactsViewModel.message.value) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(stringResource(id = R.string.message_send))
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
