package com.example.recipe.presentation.home

import androidx.lifecycle.ViewModel
import com.example.recipe.data.repository.DatabaseRepositoryImpl
import com.example.recipe.data.repository.RecipeRepositoryImpl
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel: ViewModel() {
    private val recipeRepositoryImpl = RecipeRepositoryImpl()
    private val databaseRepositoryImpl = DatabaseRepositoryImpl()

    private val searchRecipes = recipeRepositoryImpl.searchRecipes

    fun getSearchRecipes(query:String): MutableStateFlow<List<SearchRecipe>> {
        recipeRepositoryImpl.getSearchRecipes(query)
        return searchRecipes
    }

    private val savedRecipes = databaseRepositoryImpl.savedRecipes

    fun getRecipes(): MutableStateFlow<List<RecipeDetail>> {
        databaseRepositoryImpl.getRecipes()
        return savedRecipes
    }
}