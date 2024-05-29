package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.WeatherViewModel
import com.example.bagueton_v1.ui.model.WeatherAPI.readWeather
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import previewBaguetonViewModel

fun main() {

    val data = readWeather()
    if (data != null) {
        print("boisseron : ${data.weather[0].icon}")
    }

}

@Composable
fun UnboardingScreen (navHostController: NavHostController? = null,
                      baguetonViewModel: BaguetonViewModel,
                      weatherViewModel: WeatherViewModel? = null){


    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Center) {
            Image(ImageBitmap.imageResource(id = R.drawable.logo),
                contentDescription = "logo", modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .fillMaxWidth(1f)
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Text(text = "Bienvenu sur L'application Bagueton!",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(2f))

        Column {

            Row {
                Image(painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/04d@2x.png"),
                    contentDescription = "logo", modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(100.dp))
            }
            Row {
                Text(text = "16°C",Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center)
            }
            Row {
                Text(text = "Nuageux",Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center)
            }
        }
        Row {
            Text(text = "Vous avez 13 commandes aujoud'hui",Modifier.fillMaxWidth(1f),
                textAlign = TextAlign.Center)
        }


        Spacer(modifier = Modifier.weight(2f))

        Button(onClick = { navHostController?.navigate("HomeScreen") },
            modifier = Modifier
                .height(40.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Entrer")

        }
        Spacer(modifier = Modifier.weight(1f))
    }

}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun UnboardingScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            UnboardingScreen(baguetonViewModel = previewBaguetonViewModel())
        }
    }
}