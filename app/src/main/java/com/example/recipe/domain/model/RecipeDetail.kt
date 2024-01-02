package com.example.recipe.domain.model

data class RecipeDetail (
    val id: String,
    val title: String,
    val image: String,
    val imageType: String,
    val servings: Int,
    val readyInMinutes: Int
)
