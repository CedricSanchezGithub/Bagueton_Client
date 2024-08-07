package com.c3dev.bagueton.ui.ui.screens.unboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.c3dev.bagueton.R
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme
import kotlin.math.round

fun main() {

}

@Composable
fun UnboardingScreen (navHostController: NavHostController? = null,
                      weatherViewModel: UnboardingViewModel? = null){
    LaunchedEffect(key1 = true) {
        weatherViewModel?.weatherCallAPI()
    }
    val weatherData = weatherViewModel?.weatherData?.value

    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Center) {

            Image(ImageBitmap.imageResource(id = R.drawable.logobagueton),
                contentDescription = "logo", modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .fillMaxWidth(1f)
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Text(text = stringResource(id = R.string.welcome_unboarding),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))

        Row {
            Text(text = stringResource(id = R.string.order_unboarding),Modifier.fillMaxWidth(1f),
                textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(
            Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                .padding(16.dp)
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            if (weatherData != null) {
                Image(painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${weatherData.weather[0].icon}@2x.png"),
                    contentDescription = "logo", modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(100.dp))
                Text(text = weatherData.name,Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center)
                Row {
                    Text(
                        text = weatherData.main.temp.let {
                            "${(it - 273.15).let { temp -> round(temp * 10) / 10 }} °C"
                        },
                        modifier = Modifier.fillMaxWidth(1f),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Text(text = "Chargement des données météos...",
                    textAlign = TextAlign.Center
                )
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )

            }
        }


        Spacer(modifier = Modifier.weight(2f))

        Button(onClick = { navHostController?.navigate("HomeScreen") },
            modifier = Modifier
                .height(40.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.enter_unboarding))

        }
        Spacer(modifier = Modifier.weight(3f))
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UnboardingScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            UnboardingScreen()
        }
    }
}