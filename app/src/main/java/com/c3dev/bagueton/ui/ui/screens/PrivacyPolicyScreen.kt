package com.c3dev.bagueton.ui.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.c3dev.bagueton.ui.ui.MyBottomAppBar
import com.c3dev.bagueton.ui.ui.theme.Bagueton_v1Theme

@Composable
fun PrivacyPolicyScreen(navHostController : NavHostController? = null) {
    Scaffold(
        bottomBar = { MyBottomAppBar(navHostController = navHostController) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            val scrollState = rememberScrollState()

            Column(modifier = Modifier.verticalScroll(scrollState)) {
                // Titre principal
                Text(
                    text = "Politique de Confidentialité",
                    style = MaterialTheme.typography.headlineLarge

                )

                // Sous-titre et corps du texte
                Spacer(modifier = Modifier.height(32.dp))
                SectionTitle("Données Personnelles Collectées")
                sectionBody(
                    """
            Nous utilisons des services d'authentification tiers, tels que Google et Facebook, pour faciliter votre connexion à notre application. Nous ne stockons aucune donnée personnelle vous concernant sur nos serveurs à part les recettes que vous créez. Ces données ne contiennent aucune information personnelle à moins que vous ne choisissiez d'inclure de telles informations dans vos recettes.
        """.trimIndent()
                )
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Utilisation des Données")
                sectionBody("Les données collectées, c'est-à-dire les recettes, sont utilisées uniquement pour vous permettre d'accéder et de gérer vos créations culinaires dans l'application.")
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Stockage des Données")
                sectionBody("Vos recettes sont stockées sur un serveur personnel et ne sont pas partagées avec des tiers. Nous nous engageons à protéger vos données contre l'accès non autorisé, l'utilisation ou la divulgation.")
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Sécurité")
                sectionBody("Nous prenons la sécurité de vos données très au sérieux et mettons en œuvre toutes les mesures de sécurité nécessaires pour protéger vos informations.")
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Vos Droits")
                sectionBody(
                    """
            Conformément au RGPD, vous avez le droit d'accéder à vos données personnelles, de demander leur correction ou leur suppression. Vous pouvez exercer ces droits en nous contactant directement par email à [adresse email], que vous pouvez utiliser également pour toute question relative à la protection de vos données.
        """.trimIndent()
                )
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Suppression de Compte")
                sectionBody("Vous pouvez demander la suppression de votre compte et de vos données associées à tout moment en nous contactant à l'adresse email mentionnée.")
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Contact")
                sectionBody("Pour toute question concernant cette politique ou vos données personnelles, n'hésitez pas à nous contacter à admin@lynxproject.fr.")
            }
        }
    }
}
@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun sectionBody(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RgpdScreenPreview() {

    Bagueton_v1Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            PrivacyPolicyScreen()
        }
    }
}
