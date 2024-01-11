package com.example.recipe.data.repository

import com.example.recipe.data.RetrofitInstance
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe
import com.example.recipe.domain.repository.RecipeRepository

class RecipeRepositoryImpl: RecipeRepository {
    override suspend fun getSearchRecipes(query: String): List<SearchRecipe> {
        val response = RetrofitInstance.recipeApi.getSearchRecipeData(query)
        val responseSearchRecipes = response.body()
        return responseSearchRecipes?.results?.toList()!!
    }

    override suspend fun getRecipeDetail(id: String): RecipeDetail? {
        val response = RetrofitInstance.recipeApi.getRecipeDetails(id)
        return response.body()
    }
}