package com.example.recipe.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.recipe.R
import com.example.recipe.presentation.components.Loading
import com.example.recipe.presentation.detail.components.RecipeDetailCard
import com.example.recipe.presentation.ui.theme.RecipeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, recipeId: String, saved: Boolean) {
    val detailViewModel = DetailViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = {
                    Text(stringResource(R.string.recipedetail))
                }
            )
        },
    ) {

        //val recipeDetail = detailViewModel.getRecipeDetail(recipeId, saved).collectAsState()
        val recipeDetail by detailViewModel.getRecipeDetail(recipeId, saved).collectAsState()
        when {
            // If the recipes are null, show the loading state
            recipeDetail == null -> Loading()
            // If the movies are not empty, show the movie list
            else -> RecipeDetailCard(navController, detailViewModel, recipeDetail!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RecipeTheme {
    }
}