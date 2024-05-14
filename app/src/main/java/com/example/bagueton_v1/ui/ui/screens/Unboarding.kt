package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.BaguetonViewModel
import com.example.bagueton_v1.ui.previewBaguetonViewModel
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme

@Composable
fun UnboardingScreen (navHostController: NavHostController? = null, baguetonViewModel: BaguetonViewModel){

    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row {

            Spacer(modifier = Modifier.weight(2f))
            Image(ImageBitmap.imageResource(id = R.drawable.logo),contentDescription = "logo", modifier = Modifier
                .width(200.dp)
                .height(200.dp))
            Spacer(modifier = Modifier.weight(2f))
        }
        Spacer(modifier = Modifier.weight(2f))
        Text(text = "Bienvenu sur L'application Bagueton!", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(5f))
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
