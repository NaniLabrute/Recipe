package com.example.recipe.data

import com.example.recipe.commun.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Create a Retrofit instance with a base URL and a converter factory
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Create an API service from the Retrofit instance
    val recipeApi: RecipeApi = retrofit.create(RecipeApi::class.java)
}