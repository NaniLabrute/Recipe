package com.example.recipe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.repository.DatabaseRepositoryImpl
import com.example.recipe.data.repository.RecipeRepositoryImpl
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val recipeRepositoryImpl = RecipeRepositoryImpl()
    private val databaseRepositoryImpl = DatabaseRepositoryImpl()

    private val searchRecipes = MutableStateFlow<List<SearchRecipe>>(emptyList())
    private val savedRecipes = MutableStateFlow<List<RecipeDetail>>(emptyList())

    fun getSearchRecipes(query:String):MutableStateFlow<List<SearchRecipe>> {
        viewModelScope.launch {
            searchRecipes.value = recipeRepositoryImpl.getSearchRecipes(query)
        }
        return searchRecipes
    }

    fun getRecipes(): MutableStateFlow<List<RecipeDetail>> {
        viewModelScope.launch {
            savedRecipes.value = databaseRepositoryImpl.getRecipes()
        }
        return savedRecipes
    }
}