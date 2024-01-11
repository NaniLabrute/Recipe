package com.example.recipe.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.recipe.R
import com.example.recipe.presentation.components.Error
import com.example.recipe.presentation.components.Loading
import com.example.recipe.presentation.home.components.SavedRecipeList
import com.example.recipe.presentation.home.components.SearchRecipeBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState) {
    val homeViewModel = HomeViewModel()
    Scaffold() {
        Column {
            SearchRecipeBar(navController, homeViewModel, drawerState)
            Text(text = stringResource(R.string.saved_recipes))
            val savedRecipes = homeViewModel.getRecipes().collectAsState()
            if(savedRecipes.value == null){
                Loading()
            } else if(savedRecipes.value.isEmpty()){
                Error(stringResource(R.string.no_recipe_found))
            } else {
                SavedRecipeList(navController, savedRecipes.value)
            }
        }
    }
}