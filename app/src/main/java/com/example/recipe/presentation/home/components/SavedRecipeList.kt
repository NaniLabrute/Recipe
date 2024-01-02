package com.example.recipe.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe
import com.example.recipe.presentation.Screen

@Composable
fun SavedRecipeList(navController: NavController, recipes: List<RecipeDetail>) {
    LazyColumn {
        items(recipes.size) { index ->
            RecipeListItem(navController, recipes[index])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListItem(navController: NavController, recipeDetail: RecipeDetail){
    Column {
        ListItem(
            headlineContent = { Text(text = recipeDetail.title) },
            leadingContent = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Localized description",
                )
            },
            modifier = Modifier.clickable {navController.navigate(route = Screen.Detail.passData(recipeDetail.id, true))}
        )
        Divider()
    }
}