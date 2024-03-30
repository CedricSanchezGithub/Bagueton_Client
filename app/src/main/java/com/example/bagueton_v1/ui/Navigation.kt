package com.example.bagueton_v1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bagueton_v1.ui.ui.screens.HomeScreen
import com.example.bagueton_v1.ui.ui.screens.RecipeScreen2
import com.example.bagueton_v1.ui.ui.screens.RecipesScreen

//sealed permet de dire qu'une classe est héritable (ici par SearchScreen et DetailScreen)
//Uniquement par les sous classes qu'elle contient
sealed class Routes(val route: String) {
    //Route 1
    object HomeScreen : Routes("homeScreen")
    //Route 2
    object RecipesScreen : Routes("recipesScreen")
    //Route 3 avec arguments bientôt
    object RecipeScreen2 : Routes("recipesScreen2")
}

@Composable
fun AppNavigation() {

    val navHostController : NavHostController = rememberNavController()

    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.HomeScreen.route) {
        //Route 1 vers notre HomeScreen
        composable(route = Routes.HomeScreen.route)   { HomeScreen(navHostController) }
        //Route 3 vers la liste de recettes
        composable(route = Routes.RecipesScreen.route){ RecipesScreen(navHostController) }
        //Route 2 vers un écran de détail
        composable(route = Routes.RecipeScreen2.route){ RecipeScreen2(navHostController) }
    }
}