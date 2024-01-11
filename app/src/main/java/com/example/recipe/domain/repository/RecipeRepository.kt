package com.example.recipe.domain.repository

import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe

interface RecipeRepository {
    suspend fun getSearchRecipes(query: String): List<SearchRecipe>

    suspend fun getRecipeDetail(id: String): RecipeDetail?

}