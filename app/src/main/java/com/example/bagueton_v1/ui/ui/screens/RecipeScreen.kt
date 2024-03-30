package com.example.bagueton_v1.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagueton_v1.R
import com.example.bagueton_v1.ui.model.croissantRecipe
import com.example.bagueton_v1.ui.model.ingredients
import com.example.bagueton_v1.ui.screens.Bagueton_v1Theme
import com.example.bagueton_v1.ui.ui.Header
import com.example.bagueton_v1.ui.ui.MyBottomAppBar

@Composable
fun RecipeScreen(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        Spacer(Modifier.weight(1f, true))
        MyBottomAppBar()
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