package com.example.recipe.domain.model

data class SearchRecipeResponse(
    val results: List<SearchRecipe>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)