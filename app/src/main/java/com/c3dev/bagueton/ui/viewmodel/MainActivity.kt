package com.c3dev.bagueton.ui.viewmodel

import ContactsViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.c3dev.bagueton.ui.AccountViewModel
import com.c3dev.bagueton.ui.AppNavigation
import com.c3dev.bagueton.ui.BaguetonViewModel
import com.c3dev.bagueton.ui.CalendarViewModel
import com.c3dev.bagueton.ui.WeatherViewModel
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bagueton_v1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        baguetonViewModel = BaguetonViewModel(),
                        accountViewModel = AccountViewModel(),
                        weatherViewModel = WeatherViewModel(),
                        contactsViewModel = ContactsViewModel(),
                        calendarViewModel = CalendarViewModel()
                    )
                }
            }
        }
    }
}