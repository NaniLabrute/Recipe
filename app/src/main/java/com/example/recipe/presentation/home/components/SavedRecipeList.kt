package com.example.recipe.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.presentation.Screen

@Composable
fun SavedRecipeList(navController: NavController, recipes: List<RecipeDetail>) {
    LazyColumn {
        items(recipes.size) { index ->
            RecipeListItem(navController, recipes[index])
        }
    }
}

@Composable
fun RecipeListItem(navController: NavController, recipeDetail: RecipeDetail){
    Column {
        ListItem(
            headlineContent = { Text(text = recipeDetail.title) },
            leadingContent = {
                AsyncImage(
                    model  = recipeDetail.image,
                    contentDescription = recipeDetail.title,
                )
            },
            modifier = Modifier.clickable {navController.navigate(route = Screen.Detail.passData(recipeDetail.id, true))}
        )
        Divider()
    }
}