package com.example.recipe.domain.repository

import com.example.recipe.domain.model.RecipeDetail

interface RecipeRepository {
    fun getSearchRecipes(query: String)

    fun getRecipeDetail(id: String)

}