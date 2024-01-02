package com.example.recipe.presentation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipe.presentation.detail.DetailScreen
import com.example.recipe.presentation.home.HomeScreen
import com.example.recipe.presentation.settings.SettingsScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    drawerState: DrawerState
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController, drawerState)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                },
                navArgument("saved"){
                    type = NavType.BoolType
                }
            )
        ){
            val recipeId = it.arguments?.getString("id") ?: ""
            val recipeSaved = it.arguments?.getBoolean("saved") ?: false
            DetailScreen(navController, recipeId, recipeSaved)
        }
        composable(
            route = Screen.Settings.route
        ){
            SettingsScreen(drawerState)
        }
    }
}