package com.example.recipe.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.recipe.R
import com.example.recipe.presentation.components.Error
import com.example.recipe.presentation.components.Loading
import com.example.recipe.presentation.home.components.SavedRecipeList
import com.example.recipe.presentation.home.components.SearchRecipeBar
import com.example.recipe.presentation.ui.theme.RecipeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState) {
    val homeViewModel = HomeViewModel()
    Scaffold() {
        Column {
            SearchRecipeBar(navController, homeViewModel, drawerState)
            Text(text = stringResource(R.string.saved_recipes))
            val savedRecipes = homeViewModel.getRecipes().collectAsState()
            when {
                savedRecipes.value == null -> Loading()
                savedRecipes.value!!.isEmpty() -> Error(stringResource(R.string.no_recipe_found))
                else -> SavedRecipeList(navController, savedRecipes.value!!)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RecipeTheme {
    }
}