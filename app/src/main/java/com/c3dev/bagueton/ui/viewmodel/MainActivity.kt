package com.c3dev.bagueton.ui.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.c3dev.bagueton.ui.AppNavigation
import com.c3dev.bagueton.ui.model.usermanager.AccountViewModel
import com.c3dev.bagueton.ui.model.usermanager.SessionManager
import com.c3dev.bagueton.ui.ui.screens.form.ContactsViewModel
import com.c3dev.bagueton.ui.ui.screens.login.LoginViewModel
import com.c3dev.bagueton.ui.ui.screens.recipes.BaguetonViewModel
import com.c3dev.bagueton.ui.ui.screens.tools.ToolsViewModel
import com.c3dev.bagueton.ui.ui.screens.unboarding.UnboardingViewModel
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme
import com.c3dev.bagueton.ui.ui.theme.LocalThemeViewModel
import com.c3dev.bagueton.ui.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialiser SessionManager
        SessionManager.init(this)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkTheme by themeViewModel.isDarkTheme

            Bagueton_v1Theme(useDarkTheme = isDarkTheme) {
                CompositionLocalProvider(LocalThemeViewModel provides themeViewModel) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(
                            baguetonViewModel = BaguetonViewModel(),
                            accountViewModel = AccountViewModel(),
                            weatherViewModel = UnboardingViewModel(),
                            contactsViewModel = ContactsViewModel(),
                            toolsViewModel = ToolsViewModel(),
                            loginViewModel = LoginViewModel()
                        )
                    }
                }
            }
        }
    }
}
