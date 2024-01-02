package com.example.recipe.data

import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Define the API interface using Retrofit annotations
interface RecipeApi {
    @GET("/recipes/complexSearch?apiKey=c17e03660e4a4795a2561109b200a631")
    suspend fun getSearchRecipeData(@Query("query") query: String): Response<SearchRecipeResponse>

    @GET("/recipes/{id}/information?includeNutrition=false&apiKey=c17e03660e4a4795a2561109b200a631")
    suspend fun getRecipeDetails(@Path("id") id: String): Response<RecipeDetail>
}