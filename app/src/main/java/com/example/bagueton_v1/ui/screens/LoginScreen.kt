package com.example.bagueton_v1.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navHostController: NavHostController? = null) {

    var loginStat = remember { mutableStateOf("Login") }
    var passwordStat = remember { mutableStateOf("Password") }


    Column {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Bagueton",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 40.sp)

        Spacer(Modifier.weight(2f, true))
        Row {
            Spacer(Modifier.weight(1f, true))
            TextField(value = loginStat.value, onValueChange = { loginStat.value = it}, shape = RoundedCornerShape(70.dp))
            Spacer(Modifier.weight(1f, true))
        }

        Spacer(Modifier.weight(1f, true))
        Row {
            Spacer(Modifier.weight(1f, true))
            TextField(value = passwordStat.value, onValueChange = { passwordStat.value = it }, shape = RoundedCornerShape(70.dp))
            Spacer(Modifier.weight(1f, true))
        }
        Row {
            Spacer(Modifier.weight(1f, true))
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(40.dp) // Hauteur personnalisée
                    .padding(horizontal = 16.dp) // Padding horizontal
            ) {
                Text(text = "Login")
            }
        }
        Spacer(Modifier.weight(2f, true))
        MyBottomAppBar()

    }

}
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            LoginScreen()
        }
    }
}

