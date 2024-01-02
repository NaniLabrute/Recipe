package com.example.recipe.domain.repository

import com.example.recipe.domain.model.RecipeDetail
import kotlinx.coroutines.flow.MutableStateFlow

interface DatabaseRepository {
    fun getRecipes()

    fun saveRecipeDetail(recipeDetail: RecipeDetail)

    fun getRecipeDetail(id: String)

    fun deleteRecipeDetail(id: String)

    fun deleteSavedRecipes()
}