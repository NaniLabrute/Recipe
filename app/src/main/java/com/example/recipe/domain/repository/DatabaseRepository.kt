package com.example.recipe.domain.repository

import com.example.recipe.domain.model.RecipeDetail

interface DatabaseRepository {
    suspend fun getRecipes(): List<RecipeDetail>

    suspend fun saveRecipeDetail(recipeDetail: RecipeDetail)

    suspend fun getRecipeDetail(id: String):RecipeDetail?

    suspend fun deleteRecipeDetail(id: String)

    suspend fun deleteSavedRecipes()
}