package com.c3dev.bagueton.ui

import LoginScreen
import TestScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.c3dev.bagueton.ui.model.usermanager.AccountViewModel
import com.c3dev.bagueton.ui.ui.screens.form.ContactsFormScreen
import com.c3dev.bagueton.ui.ui.screens.form.ContactsViewModel
import com.c3dev.bagueton.ui.ui.screens.login.LoginViewModel
import com.c3dev.bagueton.ui.ui.screens.privacy.PrivacyPolicyScreen
import com.c3dev.bagueton.ui.ui.screens.recipes.BaguetonViewModel
import com.c3dev.bagueton.ui.ui.screens.recipes.CreateRecipeScreen
import com.c3dev.bagueton.ui.ui.screens.recipes.HomeScreen
import com.c3dev.bagueton.ui.ui.screens.recipes.ListRecipeScreen
import com.c3dev.bagueton.ui.ui.screens.recipes.RecipeScreen
import com.c3dev.bagueton.ui.ui.screens.tools.ToolScreenCalc
import com.c3dev.bagueton.ui.ui.screens.tools.ToolsScreen
import com.c3dev.bagueton.ui.ui.screens.tools.ToolsViewModel
import com.c3dev.bagueton.ui.ui.screens.unboarding.UnboardingScreen
import com.c3dev.bagueton.ui.ui.screens.unboarding.UnboardingViewModel

sealed class Routes(val route: String) {
    //Route 1
    object HomeScreen : Routes("HomeScreen")
    //Route 2
    object ListRecipeScreen : Routes("listRecipeScreen")
    //Route 3
    object CreateRecipeScreen : Routes("createRecipeScreen")
    //Route 4
    object RecipeScreen : Routes("RecipeScreen/{id}") {
        fun createRoute(id: String) = "RecipeScreen/$id"
    }
    //Route 5
    object LoginScreen : Routes("LoginScreen")
    //Route 6
    object FavoriteScreen : Routes("FavoriteScreen")
    //Route 7
    object ToolsScreen : Routes("ToolsScreen")
    //Route 7.5
    object ToolScreenCalc : Routes("ToolScreenCalc")
    //Route 8
    object PrivacyPolicyScreen : Routes("PrivacyPolicyScreen")
    //Route 9
    object UnboardingScreen : Routes("UnboardingScreen")
    //Route 11
    object TestScreen : Routes("TestScreen")
    object ContactsFormScreen : Routes("ContactsFormScreen")

}

@Composable
fun AppNavigation(baguetonViewModel: BaguetonViewModel,
                  accountViewModel: AccountViewModel,
                  weatherViewModel: UnboardingViewModel,
                  contactsViewModel: ContactsViewModel,
                  toolsViewModel: ToolsViewModel,
                  loginViewModel: LoginViewModel
) {

    val navHostController : NavHostController = rememberNavController()

    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.UnboardingScreen.route) {
        //Route 1 vers notre HomeScreen
        composable(route = Routes.HomeScreen.route)   { HomeScreen(baguetonViewModel,navHostController) }
        //Route 2 vers la recettes
        composable(
            route = Routes.RecipeScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                RecipeScreen(id, navHostController, baguetonViewModel)
            }
        }
        // Route 3 vers la liste des recettes
        composable(route = Routes.ListRecipeScreen.route) { ListRecipeScreen(navHostController, baguetonViewModel) }
        //Route 4 vers ajouter une recette
        composable(route = Routes.CreateRecipeScreen.route){ CreateRecipeScreen(navHostController, baguetonViewModel) }
        //Route 5 vers l'écran de connexion
        composable(route = Routes.LoginScreen.route){ LoginScreen(accountViewModel, navHostController, loginViewModel) }
        //Route 6 vers l'écran de favoris
        composable(route = Routes.ToolsScreen.route){ ToolsScreen(navHostController, baguetonViewModel) }
        composable(route = Routes.ToolScreenCalc.route){ ToolScreenCalc(navHostController, baguetonViewModel, toolsViewModel) }
        //Route 7 vers les politiques de confidentialitées
        composable(route = Routes.PrivacyPolicyScreen.route){ PrivacyPolicyScreen(navHostController) }
        // Route 9 vers l'écran d'unboarding
        composable(route = Routes.UnboardingScreen.route){ UnboardingScreen(navHostController, weatherViewModel) }
        //Route 10 vers l'écran de test
        composable(route = Routes.TestScreen.route){ TestScreen() }
        //Route 11 vers l'écran de contacts
        composable(route = Routes.ContactsFormScreen.route){ ContactsFormScreen(navHostController, contactsViewModel) }
    }
}
