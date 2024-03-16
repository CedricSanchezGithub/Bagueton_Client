package com.example.bagueton_v1.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagueton_v1.R

@Composable
fun RecipeScreen(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(12.dp)){
        Text(text = "Croissants",
            fontSize = 40.sp,
        )
        Image(
            painter = painterResource(R.drawable.croissant),
            contentDescription = "Croissants",
            modifier = Modifier
                .size(200.dp)
                .padding(12.dp)
        )
        Row {
            Image(
                painter = painterResource(R.drawable.croissant),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
            )
            Image(
                painter = painterResource(R.drawable.croissant),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
            )
            Image(
                painter = painterResource(R.drawable.croissant),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
            )
            Image(
                painter = painterResource(R.drawable.component_add_picture),
                contentDescription = "Croissants",
                modifier = Modifier
                    .size(80.dp)
                    .padding(12.dp)
            )
        }
        Text(text = "Mélangez farine, sucre, sel. Incorporez beurre, levure délayée. Pétrissez, laissez reposer. Étalez, pliez en trois, répétez. Roulez en croissants, laissez lever. Dorez au jaune d'œuf, cuisez à 200°C.",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(0.7f) // Utilisez 70% de la largeur disponible
     )


    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {

    Bagueton_v1Theme {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RecipeScreen()
        }
    }
}