package com.example.bagueton_v1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bagueton_v1.ui.model.RecipeBean
import com.example.bagueton_v1.ui.ui.screens.CreateRecipeScreen
import com.example.bagueton_v1.ui.ui.screens.FavoriteScreen
import com.example.bagueton_v1.ui.ui.screens.HomeScreen
import com.example.bagueton_v1.ui.ui.screens.ListRecipeScreen
import com.example.bagueton_v1.ui.ui.screens.LoginScreen
import com.example.bagueton_v1.ui.ui.screens.RecipeScreen
import com.example.bagueton_v1.ui.ui.screens.ToolsScreen

//sealed permet de dire qu'une classe est héritable (ici par SearchScreen et DetailScreen)
//Uniquement par les sous classes qu'elle contient
sealed class Routes(val route: String) {
    //Route 1
    object HomeScreen : Routes("HomeScreen")
    //Route 2
    object ListRecipeScreen : Routes("listRecipeScreen")
    //Route 3
    object CreateRecipeScreen : Routes("createRecipeScreen")
    //Route 4
    object RecipeScreen : Routes("RecipeScreen"){
        fun withObject(recipe: RecipeBean) = "MatchDetailScreen/${recipe.id_recipe}"
    }
    //Route 5
    object LoginScreen : Routes("LoginScreen")
    //Route 6
    object FavoriteScreen : Routes("FavoriteScreen")
    //Route 7
    object ToolsScreen : Routes("ToolsScreen")
}

@Composable
fun AppNavigation(baguetonViewModel: BaguetonViewModel, accountViewModel: AccountViewModel) {

    val navHostController : NavHostController = rememberNavController()

    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.HomeScreen.route) {
        //Route 1 vers notre HomeScreen
        composable(route = Routes.HomeScreen.route)   { HomeScreen(navHostController, baguetonViewModel) }
        //Route 2 vers la liste de recettes
        composable(
            route = Routes.RecipeScreen.route,
            arguments = listOf(navArgument("id_recipe") { type = NavType.LongType })
        ) {
            val idNav = it.arguments?.getLong("id_recipe") ?: 10
            RecipeScreen(id = idNav,navHostController, baguetonViewModel)
        }

        //Route 4 vers ajouter une recette
        composable(route = Routes.CreateRecipeScreen.route){ CreateRecipeScreen(navHostController, baguetonViewModel) }
        //Route 5 vers l'écran de connexion
        composable(route = Routes.LoginScreen.route){ LoginScreen(navHostController, accountViewModel) }
        //Route 6 vers l'écran de favoris
        composable(route = Routes.FavoriteScreen.route){ FavoriteScreen(navHostController, baguetonViewModel) }
        //Route 7 vers l'écran d'outils
        composable(route = Routes.ToolsScreen.route){ ToolsScreen(navHostController, baguetonViewModel) }
    }
}